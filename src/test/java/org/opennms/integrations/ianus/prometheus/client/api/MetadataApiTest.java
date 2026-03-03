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
class MetadataApiTest {

    @Mock private PrometheusRestClient restClient;

    private MetadataApi api;

    @BeforeEach
    void setUp() {
        api = new MetadataApi(restClient);
    }

    @Test
    void getMetadata_callsCorrectPath() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.getMetadata();

        assertThat(uriCaptor.getValue()).isEqualTo("/metadata");
    }

    @Test
    void getLabels_callsCorrectPath() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.getLabels();

        assertThat(uriCaptor.getValue()).isEqualTo("/labels");
    }

    @Test
    void getLabelValues_buildsCorrectPath() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.getLabelValues("job");

        assertThat(uriCaptor.getValue()).isEqualTo("/label/job/values");
    }

    @Test
    void getSeries_buildsCorrectUri() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.getSeries("up", "node_cpu_seconds_total");

        String uri = uriCaptor.getValue();
        assertThat(uri).contains("/series");
        assertThat(uri).contains("up");
    }
}
