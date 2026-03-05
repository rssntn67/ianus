package it.ianus.plugin.prometheus.client.api;

import it.ianus.plugin.prometheus.client.handler.PrometheusRestClient;
import it.ianus.plugin.prometheus.client.model.PrometheusResponse;

public class AlertsApi {

    private final PrometheusRestClient restClient;

    public AlertsApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse getAlerts() {
        return restClient.get("/alerts", PrometheusResponse.class);
    }

    public PrometheusResponse getRules() {
        return restClient.get("/rules", PrometheusResponse.class);
    }
}
