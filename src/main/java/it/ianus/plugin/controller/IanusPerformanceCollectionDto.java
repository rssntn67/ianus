package it.ianus.plugin.controller;

import java.util.List;

public record IanusPerformanceCollectionDto(
        int count,
        int offset,
        int totalCount,
        List<IanusPerformanceDto> performances
) {}