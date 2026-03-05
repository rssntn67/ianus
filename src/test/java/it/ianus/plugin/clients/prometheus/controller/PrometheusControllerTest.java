package it.ianus.plugin.clients.prometheus.controller;

import it.ianus.plugin.controller.PrometheusController;
import it.ianus.plugin.controller.PrometheusMetricCollectionDto;
import it.ianus.plugin.controller.PrometheusMetricDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import it.ianus.plugin.clients.prometheus.handler.PrometheusRestClient;
import it.ianus.plugin.clients.prometheus.model.PrometheusResponse;
import it.ianus.plugin.collectors.PrometheusCollector;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrometheusControllerTest {

    @Mock private PrometheusCollector collector;
    @Mock private PrometheusRestClient restClient;

    private PrometheusController controller;

    @BeforeEach
    void setUp() {
        controller = new PrometheusController(collector, restClient);
    }

    // --- getAllMetrics ---

    @Test
    void getAllMetrics_returnsPaginatedResults() {
        PrometheusMetricDto dto = new PrometheusMetricDto(
                1609459200L, "up", Map.of("job", "prometheus"), 1.0);
        when(collector.getAll()).thenReturn(List.of(dto));

        PrometheusMetricCollectionDto result = controller.getAllMetrics(0, 10);

        assertThat(result.totalCount()).isEqualTo(1);
        assertThat(result.count()).isEqualTo(1);
        assertThat(result.offset()).isEqualTo(0);
        assertThat(result.metrics()).hasSize(1);
        assertThat(result.metrics().get(0).metric()).isEqualTo("up");
    }

    @Test
    void getAllMetrics_paginatesCorrectly() {
        List<PrometheusMetricDto> dtos = List.of(
                new PrometheusMetricDto(1L, "a", Map.of(), 1.0),
                new PrometheusMetricDto(2L, "b", Map.of(), 2.0),
                new PrometheusMetricDto(3L, "c", Map.of(), 3.0));
        when(collector.getAll()).thenReturn(dtos);

        PrometheusMetricCollectionDto result = controller.getAllMetrics(1, 1);

        assertThat(result.totalCount()).isEqualTo(3);
        assertThat(result.count()).isEqualTo(1);
        assertThat(result.offset()).isEqualTo(1);
        assertThat(result.metrics().get(0).metric()).isEqualTo("b");
    }

    @Test
    void getAllMetrics_offsetBeyondSize_returnsEmptyPage() {
        when(collector.getAll()).thenReturn(List.of(
                new PrometheusMetricDto(1L, "a", Map.of(), 1.0)));

        PrometheusMetricCollectionDto result = controller.getAllMetrics(10, 5);

        assertThat(result.totalCount()).isEqualTo(1);
        assertThat(result.count()).isEqualTo(0);
        assertThat(result.metrics()).isEmpty();
    }

    // --- getMetrics ---

    @Test
    void getMetrics_returnsFilteredResults() {
        PrometheusMetricDto dto = new PrometheusMetricDto(
                1609459200L, "up", Map.of("job", "prometheus"), 1.0);
        when(collector.get("up")).thenReturn(List.of(dto));

        PrometheusMetricCollectionDto result = controller.getMetrics("up", 0, 10);

        assertThat(result.totalCount()).isEqualTo(1);
        assertThat(result.metrics().get(0).metric()).isEqualTo("up");
        verify(collector).get("up");
    }

    @Test
    void getMetrics_unknownMetric_returnsEmptyCollection() {
        when(collector.get("nonexistent")).thenReturn(List.of());

        PrometheusMetricCollectionDto result = controller.getMetrics("nonexistent", 0, 10);

        assertThat(result.totalCount()).isEqualTo(0);
        assertThat(result.metrics()).isEmpty();
    }

    // --- getTargets ---

    @Test
    void getTargets_delegatesToTargetsApi() {
        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        when(restClient.get(eq("/targets"), eq(PrometheusResponse.class))).thenReturn(response);

        PrometheusResponse result = controller.getTargets();

        assertThat(result.getStatus()).isEqualTo("success");
    }

    // --- getAlerts ---

    @Test
    void getAlerts_delegatesToAlertsApi() {
        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        when(restClient.get(eq("/alerts"), eq(PrometheusResponse.class))).thenReturn(response);

        PrometheusResponse result = controller.getAlerts();

        assertThat(result.getStatus()).isEqualTo("success");
    }

    // --- getRules ---

    @Test
    void getRules_delegatesToAlertsApi() {
        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        when(restClient.get(eq("/rules"), eq(PrometheusResponse.class))).thenReturn(response);

        PrometheusResponse result = controller.getRules();

        assertThat(result.getStatus()).isEqualTo("success");
    }

    // --- getLabels ---

    @Test
    void getLabels_delegatesToMetadataApi() {
        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        when(restClient.get(eq("/labels"), eq(PrometheusResponse.class))).thenReturn(response);

        PrometheusResponse result = controller.getLabels();

        assertThat(result.getStatus()).isEqualTo("success");
    }

    // --- getLabelValues ---

    @Test
    void getLabelValues_delegatesToMetadataApi() {
        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        when(restClient.get(eq("/label/job/values"), eq(PrometheusResponse.class))).thenReturn(response);

        PrometheusResponse result = controller.getLabelValues("job");

        assertThat(result.getStatus()).isEqualTo("success");
    }
}
