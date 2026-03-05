package it.ianus.plugin.controller;

import it.ianus.plugin.collectors.OpenNMSCollector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ianus/opennms")
public class OpenNMSController {

    private final OpenNMSCollector collector;

    public OpenNMSController(OpenNMSCollector collector) {
        this.collector = collector;
    }

    @GetMapping("/metrics/all")
    public IanusMetricsCollectionDto getAllMetrics(
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
            @RequestParam String resourceId,
            @RequestParam String attribute,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<IanusMetricsDto> all = collector.get(resourceId, attribute);
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<IanusMetricsDto> page = all.subList(fromIndex, toIndex);
        return new IanusMetricsCollectionDto(page.size(), offset, totalCount, page);
    }
}
