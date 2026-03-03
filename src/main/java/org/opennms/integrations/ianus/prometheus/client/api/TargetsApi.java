package org.opennms.integrations.ianus.prometheus.client.api;

import org.opennms.integrations.ianus.prometheus.client.handler.PrometheusRestClient;
import org.opennms.integrations.ianus.prometheus.client.model.PrometheusResponse;

public class TargetsApi {

    private final PrometheusRestClient restClient;

    public TargetsApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse getTargets() {
        return restClient.get("/targets", PrometheusResponse.class);
    }
}
