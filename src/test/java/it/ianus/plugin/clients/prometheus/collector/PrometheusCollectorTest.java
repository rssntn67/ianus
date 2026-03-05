package it.ianus.plugin.clients.prometheus.collector;

import it.ianus.plugin.collectors.PrometheusCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import it.ianus.plugin.IanusApplication.PrometheusProperties;
import it.ianus.plugin.clients.prometheus.handler.PrometheusRestClient;
import it.ianus.plugin.clients.prometheus.model.PrometheusResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrometheusCollectorTest {

    @Mock private PrometheusRestClient restClient;

    private PrometheusCollector collector;

    @BeforeEach
    void setUp() {
        PrometheusProperties props = new PrometheusProperties(
                "http://localhost:9090/api/v1", null, null, null, null,
                new PrometheusProperties.Collector(300000L, List.of("up")));
        collector = new PrometheusCollector(restClient, props);
    }

    @Test
    void getAll_returnsEmptyBeforeCollection() {
        assertThat(collector.getAll()).isEmpty();
    }

    @Test
    void get_returnsEmptyForUnknownMetric() {
        assertThat(collector.get("nonexistent")).isEmpty();
    }

    @Test
    void getCache_returnsUnmodifiableMap() {
        assertThatThrownBy(() -> collector.getCache().put("key", List.of()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void collect_skipsQueryWhenResponseNotSuccess() {
        PrometheusResponse failResponse = new PrometheusResponse();
        failResponse.setStatus("error");
        when(restClient.get(anyString(), eq(PrometheusResponse.class))).thenReturn(failResponse);

        collector.collect();

        assertThat(collector.getAll()).isEmpty();
    }

    @Test
    void collect_skipsQueryWhenDataIsNull() {
        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        response.setData(null);
        when(restClient.get(anyString(), eq(PrometheusResponse.class))).thenReturn(response);

        collector.collect();

        assertThat(collector.getAll()).isEmpty();
    }
}
