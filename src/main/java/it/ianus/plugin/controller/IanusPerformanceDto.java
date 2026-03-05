package it.ianus.plugin.controller;

public record IanusPerformanceDto(
        long timestamp,
        String metric,
        String instance,
        Object value
) {}