package org.opennms.integrations.ianus.prometheus.controller;

import org.opennms.integrations.ianus.prometheus.client.api.AlertsApi;
import org.opennms.integrations.ianus.prometheus.client.api.MetadataApi;
import org.opennms.integrations.ianus.prometheus.client.api.TargetsApi;
import org.opennms.integrations.ianus.prometheus.client.handler.PrometheusRestClient;
import org.opennms.integrations.ianus.prometheus.client.model.PrometheusResponse;
import org.opennms.integrations.ianus.prometheus.collector.PrometheusCollector;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ianus/prometheus")
public class PrometheusController {

    private final PrometheusCollector collector;
    private final TargetsApi targetsApi;
    private final AlertsApi alertsApi;
    private final MetadataApi metadataApi;

    public PrometheusController(PrometheusCollector collector, PrometheusRestClient restClient) {
        this.collector = collector;
        this.targetsApi = new TargetsApi(restClient);
        this.alertsApi = new AlertsApi(restClient);
        this.metadataApi = new MetadataApi(restClient);
    }

    @GetMapping("/metrics/all")
    public PrometheusMetricCollectionDto getAllMetrics(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<PrometheusMetricDto> all = collector.getAll();
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<PrometheusMetricDto> page = all.subList(fromIndex, toIndex);
        return new PrometheusMetricCollectionDto(page.size(), offset, totalCount, page);
    }

    @GetMapping("/metrics")
    public PrometheusMetricCollectionDto getMetrics(
            @RequestParam String metric,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<PrometheusMetricDto> all = collector.get(metric);
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<PrometheusMetricDto> page = all.subList(fromIndex, toIndex);
        return new PrometheusMetricCollectionDto(page.size(), offset, totalCount, page);
    }

    @GetMapping("/targets")
    public PrometheusResponse getTargets() {
        return targetsApi.getTargets();
    }

    @GetMapping("/alerts")
    public PrometheusResponse getAlerts() {
        return alertsApi.getAlerts();
    }

    @GetMapping("/rules")
    public PrometheusResponse getRules() {
        return alertsApi.getRules();
    }

    @GetMapping("/labels")
    public PrometheusResponse getLabels() {
        return metadataApi.getLabels();
    }

    @GetMapping("/label/{name}/values")
    public PrometheusResponse getLabelValues(@PathVariable String name) {
        return metadataApi.getLabelValues(name);
    }
}
