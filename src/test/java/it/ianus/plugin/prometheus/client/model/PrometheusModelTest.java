package it.ianus.plugin.prometheus.client.model;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.*;

class PrometheusModelTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void instantQueryResult_deserializes() throws Exception {
        String json = """
                {
                  "resultType": "vector",
                  "result": [
                    {
                      "metric": {"__name__": "up", "instance": "localhost:9090", "job": "prometheus"},
                      "value": [1609459200.000, "1"]
                    }
                  ]
                }
                """;
        InstantQueryResult result = mapper.readValue(json, InstantQueryResult.class);
        assertThat(result.getResultType()).isEqualTo("vector");
        assertThat(result.getResult()).hasSize(1);
        assertThat(result.getResult().get(0).getMetric()).containsEntry("__name__", "up");
        assertThat(result.getResult().get(0).getValue()).hasSize(2);
    }

    @Test
    void rangeQueryResult_deserializes() throws Exception {
        String json = """
                {
                  "resultType": "matrix",
                  "result": [
                    {
                      "metric": {"__name__": "up", "job": "prometheus"},
                      "values": [[1609459200.000, "1"], [1609459260.000, "1"]]
                    }
                  ]
                }
                """;
        RangeQueryResult result = mapper.readValue(json, RangeQueryResult.class);
        assertThat(result.getResultType()).isEqualTo("matrix");
        assertThat(result.getResult()).hasSize(1);
        assertThat(result.getResult().get(0).getValues()).hasSize(2);
    }

    @Test
    void prometheusResponse_deserializes() throws Exception {
        String json = """
                {
                  "status": "success",
                  "data": {
                    "resultType": "vector",
                    "result": []
                  }
                }
                """;
        PrometheusResponse result = mapper.readValue(json, PrometheusResponse.class);
        assertThat(result.getStatus()).isEqualTo("success");
        assertThat(result.getData()).isNotNull();
    }

    @Test
    void targetResult_deserializes() throws Exception {
        String json = """
                {
                  "activeTargets": [
                    {
                      "labels": {"instance": "localhost:9090", "job": "prometheus"},
                      "scrapePool": "prometheus",
                      "scrapeUrl": "http://localhost:9090/metrics",
                      "health": "up"
                    }
                  ],
                  "droppedTargets": []
                }
                """;
        TargetResult result = mapper.readValue(json, TargetResult.class);
        assertThat(result.getActiveTargets()).hasSize(1);
        assertThat(result.getActiveTargets().get(0).getHealth()).isEqualTo("up");
    }

    @Test
    void alert_deserializes() throws Exception {
        String json = """
                {
                  "labels": {"alertname": "HighMemory", "severity": "warning"},
                  "annotations": {"summary": "Memory usage high"},
                  "state": "firing",
                  "activeAt": "2024-01-01T00:00:00.000Z",
                  "value": "85.5"
                }
                """;
        Alert alert = mapper.readValue(json, Alert.class);
        assertThat(alert.getState()).isEqualTo("firing");
        assertThat(alert.getLabels()).containsEntry("alertname", "HighMemory");
    }
}
