package it.ianus.plugin.prometheus.client.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import it.ianus.plugin.prometheus.client.handler.PrometheusRestClient;
import it.ianus.plugin.prometheus.client.model.PrometheusResponse;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TargetsApiTest {

    @Mock private PrometheusRestClient restClient;
    private TargetsApi api;

    @BeforeEach
    void setUp() { api = new TargetsApi(restClient); }

    @Test
    void getTargets_callsCorrectPath() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.getTargets();

        assertThat(uriCaptor.getValue()).isEqualTo("/targets");
    }
}
