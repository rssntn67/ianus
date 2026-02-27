package org.opennms.integrations.ianus.controller;

import org.opennms.integrations.ianus.collector.PerformanceCollector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ianus")
public class PerformanceController {

    private final PerformanceCollector collector;

    public PerformanceController(PerformanceCollector collector) {
        this.collector = collector;
    }

    @GetMapping("/performance/all")
    public IanusPerformanceCollectionDto getAllPerformance(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<IanusPerformanceDto> all = collector.getAll();
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<IanusPerformanceDto> page = all.subList(fromIndex, toIndex);
        return new IanusPerformanceCollectionDto(page.size(), offset, totalCount, page);
    }

    @GetMapping("/performance")
    public IanusPerformanceCollectionDto getPerformance(
            @RequestParam String resourceId,
            @RequestParam String attribute,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<IanusPerformanceDto> all = collector.get(resourceId, attribute);
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<IanusPerformanceDto> page = all.subList(fromIndex, toIndex);
        return new IanusPerformanceCollectionDto(page.size(), offset, totalCount, page);
    }
}
