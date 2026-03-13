package it.ianus.plugin.collectors;

import it.ianus.plugin.IanusApplication.PrometheusProperties;
import it.ianus.plugin.clients.prometheus.api.QueryApi;
import it.ianus.plugin.clients.prometheus.handler.PrometheusRestClient;
import it.ianus.plugin.clients.prometheus.model.InstantQueryResult;
import it.ianus.plugin.clients.prometheus.model.PrometheusResponse;
import it.ianus.plugin.clients.prometheus.model.VectorSample;
import it.ianus.plugin.controller.IanusMetricsDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PrometheusCollector {

    private final QueryApi queryApi;
    private final PrometheusProperties properties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ConcurrentHashMap<String, List<IanusMetricsDto>> cache =
            new ConcurrentHashMap<>();

    public PrometheusCollector(PrometheusRestClient restClient, PrometheusProperties properties) {
        this.queryApi = new QueryApi(restClient);
        this.properties = properties;
    }

    @Scheduled(fixedDelayString = "${prometheus.collector.interval-ms:300000}")
    public void collect() {
        for (String query : properties.collector().queries()) {
            try {
                PrometheusResponse response = queryApi.instantQuery(query);
                if (!"success".equals(response.getStatus()) || response.getData() == null) continue;

                InstantQueryResult result = objectMapper.convertValue(
                        response.getData(), InstantQueryResult.class);
                storeResult(query, result);
            } catch (Exception e) {
                // log and continue with other queries
            }
        }
    }

    private void storeResult(String query, InstantQueryResult result) {
        if (result.getResult() == null) return;

        List<IanusMetricsDto> dtos = new ArrayList<>();
        for (VectorSample sample : result.getResult()) {
            if (sample.getValue() == null || sample.getValue().size() < 2) continue;

            long timestamp = ((Number) sample.getValue().get(0)).longValue();
            double value;
            try {
                value = Double.parseDouble(sample.getValue().get(1).toString());
            } catch (NumberFormatException e) {
                continue;
            }
            String metricName = sample.getMetric() != null
                    ? sample.getMetric().getOrDefault("__name__", query)
                    : query;
            String instance = sample.getMetric() != null
                    ? sample.getMetric().getOrDefault("instance", "")
                    : "";

            dtos.add(new IanusMetricsDto(timestamp, metricName, instance, 0L, value));
        }
        cache.put(query, dtos);
    }

    public Map<String, List<IanusMetricsDto>> getCache() {
        return Collections.unmodifiableMap(cache);
    }

    public List<IanusMetricsDto> getAll() {
        return cache.values().stream()
                .flatMap(List::stream)
                .toList();
    }

    public List<IanusMetricsDto> get(String metric) {
        return cache.getOrDefault(metric, List.of());
    }
}
