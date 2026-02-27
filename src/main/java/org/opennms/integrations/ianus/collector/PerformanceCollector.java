package org.opennms.integrations.ianus.collector;

import org.opennms.integrations.ianus.client.api.MeasumentsApi;
import org.opennms.integrations.ianus.client.api.ResourcesApi;
import org.opennms.integrations.ianus.client.handler.OpenNmsRestClient;
import org.opennms.integrations.ianus.client.model.QueryRequest;
import org.opennms.integrations.ianus.client.model.QueryResponse;
import org.opennms.integrations.ianus.client.model.ResourceDTOCollection;
import org.opennms.integrations.ianus.client.model.Source;
import org.opennms.integrations.ianus.controller.IanusPerformanceDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PerformanceCollector {

    static final List<String> SNMP_ATTRIBUTES = List.of(
            "ifInDiscards",
            "ifHCInOctets",
            "ifHCOutOctets",
            "ifOutErrors",
            "ifInErrors",
            "ifOutDiscards"
    );

    private final ResourcesApi resourcesApi;
    private final MeasumentsApi measumentsApi;

    private final ConcurrentHashMap<String, List<IanusPerformanceDto>> cache =
            new ConcurrentHashMap<>();

    public PerformanceCollector(OpenNmsRestClient restClient) {
        this.resourcesApi = new ResourcesApi(restClient);
        this.measumentsApi = new MeasumentsApi(restClient);
    }

    @Scheduled(fixedDelayString = "${opennms.collector.interval-ms:300000}")
    public void collect() {
        ResourceDTOCollection all = resourcesApi.getResources(null);
        if (all == null || all.getObjects() == null) return;

        long end = Instant.now().toEpochMilli();
        long start = end - 900_000L;

        all.getObjects().stream()
                .filter(node -> node.getChildren() != null && node.getChildren().getObjects() != null)
                .flatMap(node -> node.getChildren().getObjects().stream())
                .filter(r -> r.getId() != null && r.getId().contains("interfaceSnmp"))
                .forEach(resource -> collectResource(resource.getId(), start, end));
    }

    private void collectResource(String resourceId, long start, long end) {
        QueryRequest request = new QueryRequest();
        request.setStart(start);
        request.setEnd(end);
        request.setStep(300L);
        request.setMaxRows(20);
        request.setRelaxed(true);

        for (String attr : SNMP_ATTRIBUTES) {
            Source source = new Source();
            source.setResourceId(resourceId);
            source.setAttribute(attr);
            source.setLabel(attr);
            source.setTransient(false);
            request.addSourcesItem(source);
        }

        try {
            QueryResponse response = measumentsApi.query(request);
            storeResponse(resourceId, response);
        } catch (Exception e) {
            // log and continue with other resources
        }
    }

    private void storeResponse(String resourceId, QueryResponse response) {
        List<Long> timestamps = response.getTimestamps();
        List<String> labels = response.getLabels();
        if (timestamps == null || labels == null || response.getColumns() == null) return;

        for (int col = 0; col < labels.size(); col++) {
            String metric = labels.get(col);
            List<Double> values = response.getColumns().get(col).getList();
            if (values == null) continue;

            List<IanusPerformanceDto> dtos = new ArrayList<>();
            for (int i = 0; i < timestamps.size() && i < values.size(); i++) {
                dtos.add(new IanusPerformanceDto(timestamps.get(i), metric, resourceId, values.get(i)));
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
