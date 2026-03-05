package it.ianus.plugin.prometheus.client.api;

import it.ianus.plugin.prometheus.client.handler.PrometheusRestClient;
import it.ianus.plugin.prometheus.client.model.PrometheusResponse;

public class TargetsApi {

    private final PrometheusRestClient restClient;

    public TargetsApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse getTargets() {
        return restClient.get("/targets", PrometheusResponse.class);
    }
}
