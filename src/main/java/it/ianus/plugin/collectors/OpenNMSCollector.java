package it.ianus.plugin.collectors;

import it.ianus.plugin.clients.opennms.api.MeasumentsApi;
import it.ianus.plugin.clients.opennms.api.NodesApi;
import it.ianus.plugin.clients.opennms.api.ResourcesApi;
import it.ianus.plugin.clients.opennms.handler.OpenNmsRestClient;
import it.ianus.plugin.clients.opennms.model.OnmsNodeList;
import it.ianus.plugin.clients.opennms.model.QueryRequest;
import it.ianus.plugin.clients.opennms.model.QueryResponse;
import it.ianus.plugin.clients.opennms.model.ResourceDTO;
import it.ianus.plugin.clients.opennms.model.Source;
import it.ianus.plugin.controller.IanusPerformanceDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OpenNMSCollector {

    static final List<String> SNMP_ATTRIBUTES = List.of(
            "ifInDiscards",
            "ifHCInOctets",
            "ifHCOutOctets",
            "ifOutErrors",
            "ifInErrors",
            "ifOutDiscards"
    );

    static final String IF_SPEED_FRIENDLY = "ifSpeedFriendly";
    private final ResourcesApi resourcesApi;
    private final MeasumentsApi measumentsApi;
    private final NodesApi nodesApi;

    private final ConcurrentHashMap<String, List<IanusPerformanceDto>> cache =
            new ConcurrentHashMap<>();

    public OpenNMSCollector(OpenNmsRestClient restClient) {
        this.resourcesApi = new ResourcesApi(restClient);
        this.measumentsApi = new MeasumentsApi(restClient);
        this.nodesApi = new NodesApi(restClient);
    }

    @Scheduled(fixedDelayString = "${opennms.collector.interval-ms:300000}")
    public void collect() {
        OnmsNodeList nodes = nodesApi.getAllNodes();
        nodes.getObjects().forEach(node -> {
            ResourceDTO nodeDto = resourcesApi.getResourceForNode(node.getNodeId(),3);

            long end = Instant.now().toEpochMilli();
            long start = end - 900_000L;

                nodeDto.getChildren().getObjects().stream()
                .filter(r -> r.getId() != null && r.getId().contains("interfaceSnmp"))
                .forEach(resource -> collectResource(resource, start, end));});
    }

    private void collectResource(ResourceDTO resource, long start, long end) {
        QueryRequest request = new QueryRequest();
        request.setStart(start);
        request.setEnd(end);
        request.setStep(300L);
        request.setMaxRows(20);
        request.setRelaxed(true);
        if ( !resource.getStringPropertyAttributes().containsKey(IF_SPEED_FRIENDLY) || resource.getStringPropertyAttributes().get(IF_SPEED_FRIENDLY).equals("0 bps")) {
            return;
        }
        resource.getRrdGraphAttributes().keySet().stream().filter(SNMP_ATTRIBUTES::contains).forEach(k -> {
            Source source = new Source();
            source.setResourceId(resource.getId());
            source.setAttribute(k);
            source.setLabel(k);
            source.setTransient(false);
            request.addSourcesItem(source);
        });


        try {
            QueryResponse response = measumentsApi.query(request);
            storeResponse(resource.getId(), response);
        } catch (Exception e) {
            // log and continue with other resources
        }
    }

    private void storeResponse(String resourceId, QueryResponse response) {
        List<Long> timestamps = response.getTimestamps();
        List<String> labels = response.getLabels();
        if (timestamps == null || labels == null || response.getColumns() == null || timestamps.size() < 2) return;

        for (int col = 0; col < labels.size(); col++) {
            String metric = labels.get(col);
            List<Double> values = response.getColumns().get(col).getList();
            if (values == null) continue;

            List<IanusPerformanceDto> dtos = new ArrayList<>();
            for (int i = 1; i < timestamps.size() && i < values.size(); i++) {
                Double v1 = values.get(i - 1);
                Double v2 = values.get(i);
                if (v1 == null || v2 == null || v1.isNaN() || v2.isNaN()) continue;
                long dt = timestamps.get(i) - timestamps.get(i - 1);
                if (dt == 0) continue;
                double rate = (v2 - v1) / (dt / 1000.0);
                dtos.add(new IanusPerformanceDto(timestamps.get(i), metric, resourceId, rate));
            }
            cache.put(resourceId + "::" + metric, dtos);
        }
    }

    public Map<String, List<IanusPerformanceDto>> getCache() {
        return Collections.unmodifiableMap(cache);
    }

    public List<IanusPerformanceDto> getAll() {
        return cache.values().stream()
                .flatMap(List::stream)
                .toList();
    }

    public List<IanusPerformanceDto> get(String resourceId, String attribute) {
        return cache.getOrDefault(resourceId + "::" + attribute, List.of());
    }
}
