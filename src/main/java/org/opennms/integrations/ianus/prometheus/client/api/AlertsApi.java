package org.opennms.integrations.ianus.prometheus.client.api;

import org.opennms.integrations.ianus.prometheus.client.handler.PrometheusRestClient;
import org.opennms.integrations.ianus.prometheus.client.model.PrometheusResponse;

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
