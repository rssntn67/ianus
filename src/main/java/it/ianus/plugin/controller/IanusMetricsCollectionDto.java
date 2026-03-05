package it.ianus.plugin.controller;

import java.util.List;

public record IanusMetricsCollectionDto(
        int count,
        int offset,
        int totalCount,
        List<IanusMetricsDto> performances
) {}