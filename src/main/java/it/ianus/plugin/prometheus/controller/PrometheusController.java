package it.ianus.plugin.prometheus.controller;

import it.ianus.plugin.prometheus.client.api.AlertsApi;
import it.ianus.plugin.prometheus.client.api.MetadataApi;
import it.ianus.plugin.prometheus.client.api.TargetsApi;
import it.ianus.plugin.prometheus.client.handler.PrometheusRestClient;
import it.ianus.plugin.prometheus.client.model.PrometheusResponse;
import it.ianus.plugin.prometheus.collector.PrometheusCollector;
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
