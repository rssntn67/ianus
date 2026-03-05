package org.opennms.integrations.ianus.prometheus.client.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opennms.integrations.ianus.prometheus.client.handler.PrometheusRestClient;
import org.opennms.integrations.ianus.prometheus.client.model.PrometheusResponse;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertsApiTest {

    @Mock private PrometheusRestClient restClient;
    private AlertsApi api;

    @BeforeEach
    void setUp() { api = new AlertsApi(restClient); }

    @Test
    void getAlerts_callsCorrectPath() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.getAlerts();

        assertThat(uriCaptor.getValue()).isEqualTo("/alerts");
    }

    @Test
    void getRules_callsCorrectPath() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.getRules();

        assertThat(uriCaptor.getValue()).isEqualTo("/rules");
    }
}
