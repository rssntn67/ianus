package it.ianus.plugin.controller;

public record IanusMetricsDto(
        long timestamp,
        String metric,
        String instance,
        Object value
) {}