package it.ianus.plugin.prometheus.controller;

import java.util.Map;

public record PrometheusMetricDto(
        long timestamp,
        String metric,
        Map<String, String> labels,
        double value
) {}
