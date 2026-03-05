package org.opennms.integrations.ianus.prometheus.controller;

import java.util.Map;

public record PrometheusMetricDto(
        long timestamp,
        String metric,
        Map<String, String> labels,
        double value
) {}
