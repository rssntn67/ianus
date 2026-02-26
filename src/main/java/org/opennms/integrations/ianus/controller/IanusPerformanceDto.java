package org.opennms.integrations.ianus.controller;

public record IanusPerformanceDto(
        long timestamp,
        String metric,
        String instance,
        Object value
) {}