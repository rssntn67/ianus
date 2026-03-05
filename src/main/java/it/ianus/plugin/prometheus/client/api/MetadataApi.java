package it.ianus.plugin.prometheus.client.api;

import it.ianus.plugin.prometheus.client.handler.PrometheusRestClient;
import it.ianus.plugin.prometheus.client.model.PrometheusResponse;
import org.springframework.web.util.UriComponentsBuilder;

public class MetadataApi {

    private final PrometheusRestClient restClient;

    public MetadataApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse getMetadata() {
        return restClient.get("/metadata", PrometheusResponse.class);
    }

    public PrometheusResponse getLabels() {
        return restClient.get("/labels", PrometheusResponse.class);
    }

    public PrometheusResponse getLabelValues(String labelName) {
        String uri = UriComponentsBuilder.fromPath("/label/{labelName}/values")
                .buildAndExpand(labelName)
                .toUriString();
        return restClient.get(uri, PrometheusResponse.class);
    }

    public PrometheusResponse getSeries(String... matchers) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/series");
        for (String matcher : matchers) {
            builder.queryParam("match[]", matcher);
        }
        return restClient.get(builder.toUriString(), PrometheusResponse.class);
    }
}
