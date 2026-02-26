package org.opennms.integrations.ianus.controller;

import org.opennms.integrations.ianus.client.api.MeasumentsApi;
import org.opennms.integrations.ianus.client.handler.OpenNmsRestClient;
import org.opennms.integrations.ianus.client.model.QueryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ianus")
public class PerformanceController {

    private final MeasumentsApi measumentsApi;

    public PerformanceController(OpenNmsRestClient openNmsRestClient) {
        this.measumentsApi = new MeasumentsApi(openNmsRestClient);
    }

    @GetMapping("/performance")
    public IanusPerformanceCollectionDto getPerformance(
            @RequestParam String resourceId,
            @RequestParam String attribute,
            @RequestParam(required = false) Long start,
            @RequestParam(required = false) Long end,
            @RequestParam(required = false) Long step,
            @RequestParam(required = false) Integer maxrows,
            @RequestParam(required = false) String fallbackAttribute,
            @RequestParam(required = false) String aggregation,
            @RequestParam(required = false) Boolean relaxed,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        QueryResponse response = measumentsApi.simpleQuery(
                resourceId, attribute, start, end, step, maxrows, fallbackAttribute, aggregation, relaxed);

        List<IanusPerformanceDto> all = new ArrayList<>();

        List<Long> timestamps = response.getTimestamps();
        List<String> labels = response.getLabels();

        if (timestamps != null && labels != null && response.getColumns() != null) {
            for (int col = 0; col < labels.size(); col++) {
                String metric = labels.get(col);
                List<Double> values = response.getColumns().get(col).getList();
                if (values == null) continue;
                for (int i = 0; i < timestamps.size() && i < values.size(); i++) {
                    all.add(new IanusPerformanceDto(timestamps.get(i), metric, resourceId, values.get(i)));
                }
            }
        }

        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<IanusPerformanceDto> page = all.subList(fromIndex, toIndex);

        return new IanusPerformanceCollectionDto(page.size(), offset, totalCount, page);
    }
}