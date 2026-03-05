package it.ianus.plugin.clients.prometheus.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import it.ianus.plugin.clients.prometheus.handler.PrometheusRestClient;
import it.ianus.plugin.clients.prometheus.model.PrometheusResponse;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QueryApiTest {

    @Mock private PrometheusRestClient restClient;

    private QueryApi api;

    @BeforeEach
    void setUp() {
        api = new QueryApi(restClient);
    }

    @Test
    void instantQuery_buildsCorrectUri() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.instantQuery("up");

        assertThat(uriCaptor.getValue()).contains("/query").contains("query=up");
    }

    @Test
    void rangeQuery_buildsCorrectUri() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.rangeQuery("up", "2024-01-01T00:00:00Z", "2024-01-01T01:00:00Z", "60s");

        String uri = uriCaptor.getValue();
        assertThat(uri).contains("/query_range");
        assertThat(uri).contains("query=up");
        assertThat(uri).contains("start=");
        assertThat(uri).contains("end=");
        assertThat(uri).contains("step=60s");
    }
}
