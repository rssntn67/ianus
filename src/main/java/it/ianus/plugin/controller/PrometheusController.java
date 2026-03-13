package it.ianus.plugin.controller;

import it.ianus.plugin.clients.prometheus.api.AlertsApi;
import it.ianus.plugin.clients.prometheus.api.MetadataApi;
import it.ianus.plugin.clients.prometheus.api.TargetsApi;
import it.ianus.plugin.clients.prometheus.handler.PrometheusRestClient;
import it.ianus.plugin.clients.prometheus.model.PrometheusResponse;
import it.ianus.plugin.collectors.PrometheusCollector;
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
    public IanusMetricsCollectionDto getAllMetrics(
            @RequestParam(defaultValue = "300000") long interval,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<IanusMetricsDto> all = collector.getAll();
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<IanusMetricsDto> page = all.subList(fromIndex, toIndex);
        return new IanusMetricsCollectionDto(page.size(), offset, totalCount, page);
    }

    @GetMapping("/metrics")
    public IanusMetricsCollectionDto getMetrics(
            @RequestParam(defaultValue = "300000") long interval,
            @RequestParam String metric,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<IanusMetricsDto> all = collector.get(metric);
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<IanusMetricsDto> page = all.subList(fromIndex, toIndex);
        return new IanusMetricsCollectionDto(page.size(), offset, totalCount, page);
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
