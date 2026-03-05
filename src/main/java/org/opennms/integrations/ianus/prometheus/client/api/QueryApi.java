package org.opennms.integrations.ianus.prometheus.client.api;

import org.opennms.integrations.ianus.prometheus.client.handler.PrometheusRestClient;
import org.opennms.integrations.ianus.prometheus.client.model.PrometheusResponse;
import org.springframework.web.util.UriComponentsBuilder;

public class QueryApi {

    private final PrometheusRestClient restClient;

    public QueryApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse instantQuery(String query) {
        String uri = UriComponentsBuilder.fromPath("/query")
                .queryParam("query", query)
                .toUriString();
        return restClient.get(uri, PrometheusResponse.class);
    }

    public PrometheusResponse rangeQuery(String query, String start, String end, String step) {
        String uri = UriComponentsBuilder.fromPath("/query_range")
                .queryParam("query", query)
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("step", step)
                .toUriString();
        return restClient.get(uri, PrometheusResponse.class);
    }
}
