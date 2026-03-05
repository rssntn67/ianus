package it.ianus.plugin.controller;

import java.util.List;

public record PrometheusMetricCollectionDto(
        int count,
        int offset,
        int totalCount,
        List<PrometheusMetricDto> metrics
) {}
