package org.opennms.integrations.ianus.prometheus.collector;

import org.opennms.integrations.ianus.prometheus.PrometheusProperties;
import org.opennms.integrations.ianus.prometheus.client.api.QueryApi;
import org.opennms.integrations.ianus.prometheus.client.handler.PrometheusRestClient;
import org.opennms.integrations.ianus.prometheus.client.model.InstantQueryResult;
import org.opennms.integrations.ianus.prometheus.client.model.PrometheusResponse;
import org.opennms.integrations.ianus.prometheus.client.model.VectorSample;
import org.opennms.integrations.ianus.prometheus.controller.PrometheusMetricDto;
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

    private final ConcurrentHashMap<String, List<PrometheusMetricDto>> cache =
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

        List<PrometheusMetricDto> dtos = new ArrayList<>();
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

            dtos.add(new PrometheusMetricDto(timestamp, metricName, sample.getMetric(), value));
        }
        cache.put(query, dtos);
    }

    public Map<String, List<PrometheusMetricDto>> getCache() {
        return Collections.unmodifiableMap(cache);
    }

    public List<PrometheusMetricDto> getAll() {
        return cache.values().stream()
                .flatMap(List::stream)
                .toList();
    }

    public List<PrometheusMetricDto> get(String metric) {
        return cache.getOrDefault(metric, List.of());
    }
}
