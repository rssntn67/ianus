package it.ianus.plugin.clients.prometheus.api;

import it.ianus.plugin.clients.prometheus.handler.PrometheusRestClient;
import it.ianus.plugin.clients.prometheus.model.PrometheusResponse;

public class TargetsApi {

    private final PrometheusRestClient restClient;

    public TargetsApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse getTargets() {
        return restClient.get("/targets", PrometheusResponse.class);
    }
}
