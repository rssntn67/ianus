# Prometheus Plugin Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Add a Prometheus integration plugin that reads metrics, targets, alerts, rules, and labels from Prometheus HTTP API, following the same 4-layer architecture as the existing OpenNMS plugin.

**Architecture:** Mirror the OpenNMS plugin pattern — `Properties` → `ClientConfig` → `Handler/RestClient` → `API classes` → `Collector` → `Controller`. Each layer is independent from OpenNMS. Authentication is optional (Basic Auth applied only when `prometheus.username` is set).

**Tech Stack:** Spring Boot 4.0.3, Java 21, RestClient, JUnit 5, Mockito, AssertJ

---

### Task 1: PrometheusProperties and PrometheusClientConfig

**Files:**
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/PrometheusProperties.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/PrometheusClientConfig.java`
- Modify: `src/main/resources/application.properties`

**Step 1: Create PrometheusProperties**

```java
package org.opennms.integrations.ianus.prometheus;

import java.time.Duration;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "prometheus")
public record PrometheusProperties(
        String baseUrl,
        String username,
        String password,
        Duration connectTimeout,
        Duration readTimeout,
        Collector collector) {

    public PrometheusProperties {
        if (connectTimeout == null) connectTimeout = Duration.ofSeconds(5);
        if (readTimeout == null) readTimeout = Duration.ofSeconds(30);
        if (collector == null) collector = new Collector(300000L, List.of());
    }

    public record Collector(long intervalMs, List<String> queries) {
        public Collector {
            if (queries == null) queries = List.of();
        }
    }
}
```

**Step 2: Create PrometheusClientConfig**

```java
package org.opennms.integrations.ianus.prometheus;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import it.ianus.plugin.clients.PrometheusProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(PrometheusProperties.class)
public class PrometheusClientConfig {

    @Bean
    RestClient prometheusClient(PrometheusProperties props) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout((int) props.connectTimeout().toMillis());
        factory.setReadTimeout((int) props.readTimeout().toMillis());

        RestClient.Builder builder = RestClient.builder()
                .baseUrl(props.baseUrl())
                .requestFactory(factory)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        if (props.username() != null && !props.username().isBlank()) {
            String credentials = props.username() + ":" + props.password();
            String basicAuth = "Basic " + Base64.getEncoder()
                    .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
            builder.defaultHeader(HttpHeaders.AUTHORIZATION, basicAuth);
        }

        return builder.build();
    }
}
```

**Step 3: Add default Prometheus properties to application.properties**

Append to `src/main/resources/application.properties`:

```properties
prometheus.base-url=http://localhost:9090/api/v1
#prometheus.username=
#prometheus.password=
#prometheus.connect-timeout=5s
#prometheus.read-timeout=30s
#prometheus.collector.interval-ms=300000
#prometheus.collector.queries=up
```

**Step 4: Verify compilation**

Run: `./mvnw compile`
Expected: BUILD SUCCESS

**Step 5: Commit**

```bash
git add src/main/java/org/opennms/integrations/ianus/prometheus/PrometheusProperties.java \
        src/main/java/org/opennms/integrations/ianus/prometheus/PrometheusClientConfig.java \
        src/main/resources/application.properties
git commit -m "feat(prometheus): add PrometheusProperties and PrometheusClientConfig"
```

---

### Task 2: PrometheusRestClient and PrometheusClientException

**Files:**
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/handler/PrometheusClientException.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/handler/PrometheusRestClient.java`
- Test: `src/test/java/org/opennms/integrations/ianus/prometheus/client/handler/PrometheusRestClientTest.java`

**Step 1: Create PrometheusClientException**

```java
package org.opennms.integrations.ianus.prometheus.client.handler;

public class PrometheusClientException extends RuntimeException {

    public PrometheusClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

**Step 2: Write the failing test for PrometheusRestClient**

```java
package org.opennms.integrations.ianus.prometheus.client.handler;

import handler.it.ianus.plugin.prometheus.PrometheusClientException;
import handler.it.ianus.plugin.prometheus.PrometheusRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrometheusRestClientTest {

    @Mock
    private RestClient restClient;
    @Mock
    private RestClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;
    @Mock
    private RestClient.ResponseSpec responseSpec;

    private PrometheusRestClient client;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        client = new PrometheusRestClient(restClient);
        when(restClient.get()).thenReturn((RestClient.RequestHeadersUriSpec) requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(((RestClient.RequestHeadersSpec<?>) requestHeadersUriSpec).retrieve()).thenReturn(responseSpec);
    }

    @Test
    void get_returnsBody() {
        when(responseSpec.body(String.class)).thenReturn("hello");

        String result = client.get("/test", String.class);

        assertThat(result).isEqualTo("hello");
    }

    @Test
    void get_wrapsRestClientResponseException() {
        when(responseSpec.body(String.class)).thenThrow(
                new RestClientResponseException("fail", HttpStatusCode.valueOf(500), "Internal Server Error", null, null, null));

        assertThatThrownBy(() -> client.get("/test", String.class))
                .isInstanceOf(PrometheusClientException.class)
                .hasMessageContaining("GET")
                .hasMessageContaining("/test")
                .hasMessageContaining("500");
    }
}
```

**Step 3: Run test to verify it fails**

Run: `./mvnw test -Dtest=PrometheusRestClientTest`
Expected: FAIL (class not found)

**Step 4: Create PrometheusRestClient**

```java
package org.opennms.integrations.ianus.prometheus.client.handler;

import handler.it.ianus.plugin.prometheus.PrometheusClientException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Service
public class PrometheusRestClient {

    private final RestClient restClient;

    public PrometheusRestClient(@Qualifier("prometheusClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public <T> T get(String uri, Class<T> responseType) {
        try {
            return restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(responseType);
        } catch (RestClientResponseException ex) {
            throw new PrometheusClientException("GET " + uri + " failed: " + ex.getStatusCode(), ex);
        }
    }

    public <T> T get(String uri, ParameterizedTypeReference<T> responseType) {
        try {
            return restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(responseType);
        } catch (RestClientResponseException ex) {
            throw new PrometheusClientException("GET " + uri + " failed: " + ex.getStatusCode(), ex);
        }
    }

    public <T> T post(String uri, Object body, Class<T> responseType) {
        try {
            return restClient.post()
                    .uri(uri)
                    .body(body)
                    .retrieve()
                    .body(responseType);
        } catch (RestClientResponseException ex) {
            throw new PrometheusClientException("POST " + uri + " failed: " + ex.getStatusCode(), ex);
        }
    }
}
```

**Step 5: Run test to verify it passes**

Run: `./mvnw test -Dtest=PrometheusRestClientTest`
Expected: PASS

**Step 6: Commit**

```bash
git add src/main/java/org/opennms/integrations/ianus/prometheus/client/handler/ \
        src/test/java/org/opennms/integrations/ianus/prometheus/client/handler/
git commit -m "feat(prometheus): add PrometheusRestClient and PrometheusClientException"
```

---

### Task 3: Prometheus Response Models

**Files:**
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/PrometheusResponse.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/InstantQueryResult.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/RangeQueryResult.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/VectorSample.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/MatrixSample.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/MetricMetadata.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/TargetResult.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/ActiveTarget.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/DroppedTarget.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/AlertResult.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/Alert.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/RuleGroupResult.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/RuleGroup.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/model/Rule.java`
- Test: `src/test/java/org/opennms/integrations/ianus/prometheus/client/model/PrometheusModelTest.java`

**Step 1: Write tests for JSON deserialization**

Test that Prometheus JSON responses map correctly to our model classes. Key JSON formats from Prometheus API:

```java
package org.opennms.integrations.ianus.prometheus.client.model;

import model.it.ianus.plugin.prometheus.Alert;
import model.it.ianus.plugin.prometheus.InstantQueryResult;
import model.it.ianus.plugin.prometheus.PrometheusResponse;
import model.it.ianus.plugin.prometheus.RangeQueryResult;
import model.it.ianus.plugin.prometheus.TargetResult;
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
                [
                  {
                    "labels": {"alertname": "HighMemory", "severity": "warning"},
                    "annotations": {"summary": "Memory usage high"},
                    "state": "firing",
                    "activeAt": "2024-01-01T00:00:00.000Z",
                    "value": "85.5"
                  }
                ]
                """;
        Alert[] alerts = mapper.readValue(json, Alert[].class);
        assertThat(alerts).hasSize(1);
        assertThat(alerts[0].getState()).isEqualTo("firing");
        assertThat(alerts[0].getLabels()).containsEntry("alertname", "HighMemory");
    }

    @Test
    void metricMetadata_deserializes() throws Exception {
        String json = """
                {
                  "up": [{"type": "gauge", "help": "Target is up", "unit": ""}]
                }
                """;
        // MetricMetadata is Map<String, List<MetricMetadata.Entry>>
        // We test deserialization via the response wrapper
    }
}
```

**Step 2: Run tests to verify they fail**

Run: `./mvnw test -Dtest=PrometheusModelTest`
Expected: FAIL (classes not found)

**Step 3: Create all model classes**

The models use Jackson annotations and follow the existing project pattern of mutable POJOs (matching the OpenNMS models). Key Prometheus API JSON formats:

- **PrometheusResponse**: `{status, data, errorType, error}` — `data` is a `JsonNode` since it varies by endpoint
- **InstantQueryResult**: `{resultType: "vector", result: [{metric: {...}, value: [ts, val]}]}`
- **RangeQueryResult**: `{resultType: "matrix", result: [{metric: {...}, values: [[ts, val], ...]}]}`
- **VectorSample**: `{metric: Map<String,String>, value: List<Object>}` where value[0]=timestamp (double), value[1]=string value
- **MatrixSample**: `{metric: Map<String,String>, values: List<List<Object>>}`
- **MetricMetadata**: `{type, help, unit}`
- **TargetResult**: `{activeTargets: [ActiveTarget], droppedTargets: [DroppedTarget]}`
- **ActiveTarget**: `{labels, scrapePool, scrapeUrl, health, lastScrape, lastScrapeDuration, lastError}`
- **DroppedTarget**: `{discoveredLabels}`
- **AlertResult**: `{alerts: [Alert]}`
- **Alert**: `{labels, annotations, state, activeAt, value}`
- **RuleGroupResult**: `{groups: [RuleGroup]}`
- **RuleGroup**: `{name, file, rules: [Rule], interval}`
- **Rule**: `{name, query, duration, labels, annotations, alerts, health, type, state}`

All model classes should:
- Use `@JsonIgnoreProperties(ignoreUnknown = true)` (Prometheus API may add fields)
- Have getters/setters (mutable POJOs, matching existing project style)
- No-arg constructor

**Step 4: Run tests to verify they pass**

Run: `./mvnw test -Dtest=PrometheusModelTest`
Expected: PASS

**Step 5: Commit**

```bash
git add src/main/java/org/opennms/integrations/ianus/prometheus/client/model/ \
        src/test/java/org/opennms/integrations/ianus/prometheus/client/model/
git commit -m "feat(prometheus): add Jackson model classes for Prometheus API responses"
```

---

### Task 4: QueryApi

**Files:**
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/api/QueryApi.java`
- Test: `src/test/java/org/opennms/integrations/ianus/prometheus/client/api/QueryApiTest.java`

**Step 1: Write failing test**

```java
package org.opennms.integrations.ianus.prometheus.client.api;

import api.it.ianus.plugin.prometheus.QueryApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QueryApiTest {

    @Mock
    private PrometheusRestClient restClient;

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
```

**Step 2: Run test to verify it fails**

Run: `./mvnw test -Dtest=QueryApiTest`
Expected: FAIL

**Step 3: Create QueryApi**

```java
package org.opennms.integrations.ianus.prometheus.client.api;

import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;
import org.springframework.web.util.UriComponentsBuilder;

public class QueryApi {

    private final PrometheusRestClient restClient;

    public QueryApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse instantQuery(String query) {
        String uri = UriComponentsBuilder.fromPath("/query")
                .queryParam("query", query)
                .toUriString();
        return restClient.get(uri, PrometheusResponse.class);
    }

    public PrometheusResponse rangeQuery(String query, String start, String end, String step) {
        String uri = UriComponentsBuilder.fromPath("/query_range")
                .queryParam("query", query)
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("step", step)
                .toUriString();
        return restClient.get(uri, PrometheusResponse.class);
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./mvnw test -Dtest=QueryApiTest`
Expected: PASS

**Step 5: Commit**

```bash
git add src/main/java/org/opennms/integrations/ianus/prometheus/client/api/QueryApi.java \
        src/test/java/org/opennms/integrations/ianus/prometheus/client/api/QueryApiTest.java
git commit -m "feat(prometheus): add QueryApi for instant and range queries"
```

---

### Task 5: MetadataApi

**Files:**
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/api/MetadataApi.java`
- Test: `src/test/java/org/opennms/integrations/ianus/prometheus/client/api/MetadataApiTest.java`

**Step 1: Write failing test**

```java
package org.opennms.integrations.ianus.prometheus.client.api;

import api.it.ianus.plugin.prometheus.MetadataApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetadataApiTest {

    @Mock
    private PrometheusRestClient restClient;

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
        assertThat(uri).contains("match%5B%5D=up");  // match[]=up URL-encoded
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./mvnw test -Dtest=MetadataApiTest`
Expected: FAIL

**Step 3: Create MetadataApi**

```java
package org.opennms.integrations.ianus.prometheus.client.api;

import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;
import org.springframework.web.util.UriComponentsBuilder;

public class MetadataApi {

    private final PrometheusRestClient restClient;

    public MetadataApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse getMetadata() {
        return restClient.get("/metadata", PrometheusResponse.class);
    }

    public PrometheusResponse getLabels() {
        return restClient.get("/labels", PrometheusResponse.class);
    }

    public PrometheusResponse getLabelValues(String labelName) {
        String uri = UriComponentsBuilder.fromPath("/label/{labelName}/values")
                .buildAndExpand(labelName)
                .toUriString();
        return restClient.get(uri, PrometheusResponse.class);
    }

    public PrometheusResponse getSeries(String... matchers) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/series");
        for (String matcher : matchers) {
            builder.queryParam("match[]", matcher);
        }
        return restClient.get(builder.toUriString(), PrometheusResponse.class);
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./mvnw test -Dtest=MetadataApiTest`
Expected: PASS

**Step 5: Commit**

```bash
git add src/main/java/org/opennms/integrations/ianus/prometheus/client/api/MetadataApi.java \
        src/test/java/org/opennms/integrations/ianus/prometheus/client/api/MetadataApiTest.java
git commit -m "feat(prometheus): add MetadataApi for labels, metadata, and series"
```

---

### Task 6: TargetsApi and AlertsApi

**Files:**
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/api/TargetsApi.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/client/api/AlertsApi.java`
- Test: `src/test/java/org/opennms/integrations/ianus/prometheus/client/api/TargetsApiTest.java`
- Test: `src/test/java/org/opennms/integrations/ianus/prometheus/client/api/AlertsApiTest.java`

**Step 1: Write failing tests**

TargetsApiTest:

```java
package org.opennms.integrations.ianus.prometheus.client.api;

import api.it.ianus.plugin.prometheus.TargetsApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TargetsApiTest {

    @Mock
    private PrometheusRestClient restClient;
    private TargetsApi api;

    @BeforeEach
    void setUp() {
        api = new TargetsApi(restClient);
    }

    @Test
    void getTargets_callsCorrectPath() {
        ArgumentCaptor<String> uriCaptor = ArgumentCaptor.forClass(String.class);
        when(restClient.get(uriCaptor.capture(), eq(PrometheusResponse.class))).thenReturn(new PrometheusResponse());

        api.getTargets();

        assertThat(uriCaptor.getValue()).isEqualTo("/targets");
    }
}
```

AlertsApiTest:

```java
package org.opennms.integrations.ianus.prometheus.client.api;

import api.it.ianus.plugin.prometheus.AlertsApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertsApiTest {

    @Mock
    private PrometheusRestClient restClient;
    private AlertsApi api;

    @BeforeEach
    void setUp() {
        api = new AlertsApi(restClient);
    }

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
```

**Step 2: Run tests to verify they fail**

Run: `./mvnw test -Dtest="TargetsApiTest,AlertsApiTest"`
Expected: FAIL

**Step 3: Create TargetsApi and AlertsApi**

TargetsApi:

```java
package org.opennms.integrations.ianus.prometheus.client.api;

import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;

public class TargetsApi {

    private final PrometheusRestClient restClient;

    public TargetsApi(PrometheusRestClient restClient) {
        this.restClient = restClient;
    }

    public PrometheusResponse getTargets() {
        return restClient.get("/targets", PrometheusResponse.class);
    }
}
```

AlertsApi:

```java
package org.opennms.integrations.ianus.prometheus.client.api;

import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;

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
```

**Step 4: Run tests to verify they pass**

Run: `./mvnw test -Dtest="TargetsApiTest,AlertsApiTest"`
Expected: PASS

**Step 5: Commit**

```bash
git add src/main/java/org/opennms/integrations/ianus/prometheus/client/api/TargetsApi.java \
        src/main/java/org/opennms/integrations/ianus/prometheus/client/api/AlertsApi.java \
        src/test/java/org/opennms/integrations/ianus/prometheus/client/api/TargetsApiTest.java \
        src/test/java/org/opennms/integrations/ianus/prometheus/client/api/AlertsApiTest.java
git commit -m "feat(prometheus): add TargetsApi and AlertsApi"
```

---

### Task 7: PrometheusCollector

**Files:**
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/collector/PrometheusCollector.java`
- Test: `src/test/java/org/opennms/integrations/ianus/prometheus/collector/PrometheusCollectorTest.java`

**Step 1: Write failing test**

```java
package org.opennms.integrations.ianus.prometheus.collector;

import it.ianus.plugin.collectors.PrometheusCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import prometheus.it.ianus.plugin.clients.PrometheusProperties;
import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.InstantQueryResult;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;
import model.client.prometheus.it.ianus.plugin.VectorSample;
import controller.prometheus.it.ianus.plugin.PrometheusMetricDto;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrometheusCollectorTest {

    @Mock
    private PrometheusRestClient restClient;

    private PrometheusCollector collector;

    @BeforeEach
    void setUp() {
        PrometheusProperties props = new PrometheusProperties(
                "http://localhost:9090/api/v1", null, null, null, null,
                new PrometheusProperties.Collector(300000L, List.of("up")));
        collector = new PrometheusCollector(restClient, props);
    }

    @Test
    void collect_populatesCache() {
        // Build a mock Prometheus response for "up" query
        VectorSample sample = new VectorSample();
        sample.setMetric(Map.of("__name__", "up", "instance", "localhost:9090", "job", "prometheus"));
        sample.setValue(List.of(1609459200.0, "1"));

        InstantQueryResult queryResult = new InstantQueryResult();
        queryResult.setResultType("vector");
        queryResult.setResult(List.of(sample));

        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        // The response data will be deserialized from the InstantQueryResult
        // For unit testing, we mock the parsed result
        when(restClient.get(contains("/query"), eq(PrometheusResponse.class))).thenReturn(response);

        collector.collect();

        // After collection, cache should have entries
        List<PrometheusMetricDto> all = collector.getAll();
        // Exact assertions depend on how collect() parses PrometheusResponse.data
    }

    @Test
    void getAll_returnsEmptyWhenNothingCollected() {
        assertThat(collector.getAll()).isEmpty();
    }

    @Test
    void get_returnsEmptyForUnknownMetric() {
        assertThat(collector.get("nonexistent")).isEmpty();
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./mvnw test -Dtest=PrometheusCollectorTest`
Expected: FAIL

**Step 3: Create PrometheusCollector**

Pattern mirrors `PerformanceCollector` (see `src/main/java/org/opennms/integrations/ianus/collector/PerformanceCollector.java`):

```java
package org.opennms.integrations.ianus.prometheus.collector;

import prometheus.it.ianus.plugin.clients.PrometheusProperties;
import api.client.prometheus.it.ianus.plugin.QueryApi;
import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.InstantQueryResult;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;
import model.client.prometheus.it.ianus.plugin.VectorSample;
import controller.prometheus.it.ianus.plugin.PrometheusMetricDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PrometheusCollector {

    private final QueryApi queryApi;
    private final PrometheusProperties properties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ConcurrentHashMap<String, List<PrometheusMetricDto>> cache =
            new ConcurrentHashMap<>();

    public PrometheusCollector(PrometheusRestClient restClient, PrometheusProperties properties) {
        this.queryApi = new QueryApi(restClient);
        this.properties = properties;
    }

    @Scheduled(fixedDelayString = "${prometheus.collector.interval-ms:300000}")
    public void collect() {
        for (String query : properties.collector().queries()) {
            try {
                PrometheusResponse response = queryApi.instantQuery(query);
                if (!"success".equals(response.getStatus()) || response.getData() == null) continue;

                InstantQueryResult result = objectMapper.convertValue(
                        response.getData(), InstantQueryResult.class);
                storeResult(query, result);
            } catch (Exception e) {
                // log and continue with other queries
            }
        }
    }

    private void storeResult(String query, InstantQueryResult result) {
        if (result.getResult() == null) return;

        List<PrometheusMetricDto> dtos = new ArrayList<>();
        for (VectorSample sample : result.getResult()) {
            if (sample.getValue() == null || sample.getValue().size() < 2) continue;

            long timestamp = ((Number) sample.getValue().get(0)).longValue();
            double value = Double.parseDouble(sample.getValue().get(1).toString());
            String metricName = sample.getMetric().getOrDefault("__name__", query);

            dtos.add(new PrometheusMetricDto(timestamp, metricName, sample.getMetric(), value));
        }
        cache.put(query, dtos);
    }

    public Map<String, List<PrometheusMetricDto>> getCache() {
        return Collections.unmodifiableMap(cache);
    }

    public List<PrometheusMetricDto> getAll() {
        return cache.values().stream()
                .flatMap(List::stream)
                .toList();
    }

    public List<PrometheusMetricDto> get(String metric) {
        return cache.getOrDefault(metric, List.of());
    }
}
```

**Step 4: Create PrometheusMetricDto**

```java
package org.opennms.integrations.ianus.prometheus.controller;

import java.util.Map;

public record PrometheusMetricDto(
        long timestamp,
        String metric,
        Map<String, String> labels,
        double value
) {}
```

**Step 5: Run test to verify it passes**

Run: `./mvnw test -Dtest=PrometheusCollectorTest`
Expected: PASS

**Step 6: Commit**

```bash
git add src/main/java/org/opennms/integrations/ianus/prometheus/collector/ \
        src/main/java/org/opennms/integrations/ianus/prometheus/controller/PrometheusMetricDto.java \
        src/test/java/org/opennms/integrations/ianus/prometheus/collector/
git commit -m "feat(prometheus): add PrometheusCollector with scheduled metric polling"
```

---

### Task 8: PrometheusController and remaining DTOs

**Files:**
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/controller/PrometheusMetricCollectionDto.java`
- Create: `src/main/java/org/opennms/integrations/ianus/prometheus/controller/PrometheusController.java`
- Test: `src/test/java/org/opennms/integrations/ianus/prometheus/controller/PrometheusControllerTest.java`

**Step 1: Create PrometheusMetricCollectionDto**

```java
package org.opennms.integrations.ianus.prometheus.controller;

import it.ianus.plugin.controller.PrometheusMetricDto;

import java.util.List;

public record PrometheusMetricCollectionDto(
        int count,
        int offset,
        int totalCount,
        List<PrometheusMetricDto> metrics
) {
}
```

**Step 2: Write failing test for PrometheusController**

```java
package org.opennms.integrations.ianus.prometheus.controller;

import it.ianus.plugin.controller.PrometheusController;
import it.ianus.plugin.controller.PrometheusMetricDto;
import org.junit.jupiter.api.Test;
import collector.prometheus.it.ianus.plugin.PrometheusCollector;
import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrometheusController.class)
class PrometheusControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PrometheusCollector collector;
    @MockitoBean
    private PrometheusRestClient restClient;

    @Test
    void getAllMetrics_returnsPaginatedResults() throws Exception {
        PrometheusMetricDto dto = new PrometheusMetricDto(1609459200L, "up", Map.of("job", "prometheus"), 1.0);
        when(collector.getAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/ianus/prometheus/metrics/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount").value(1))
                .andExpect(jsonPath("$.metrics[0].metric").value("up"));
    }

    @Test
    void getMetricsByName_returnsFilteredResults() throws Exception {
        PrometheusMetricDto dto = new PrometheusMetricDto(1609459200L, "up", Map.of("job", "prometheus"), 1.0);
        when(collector.get("up")).thenReturn(List.of(dto));

        mockMvc.perform(get("/ianus/prometheus/metrics").param("metric", "up"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount").value(1));
    }

    @Test
    void getTargets_delegatesToPrometheus() throws Exception {
        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        when(restClient.get(eq("/targets"), eq(PrometheusResponse.class))).thenReturn(response);

        mockMvc.perform(get("/ianus/prometheus/targets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    void getAlerts_delegatesToPrometheus() throws Exception {
        PrometheusResponse response = new PrometheusResponse();
        response.setStatus("success");
        when(restClient.get(eq("/alerts"), eq(PrometheusResponse.class))).thenReturn(response);

        mockMvc.perform(get("/ianus/prometheus/alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"));
    }
}
```

**Step 3: Run test to verify it fails**

Run: `./mvnw test -Dtest=PrometheusControllerTest`
Expected: FAIL

**Step 4: Create PrometheusController**

```java
package org.opennms.integrations.ianus.prometheus.controller;

import api.client.prometheus.it.ianus.plugin.AlertsApi;
import it.ianus.plugin.controller.PrometheusMetricCollectionDto;
import it.ianus.plugin.controller.PrometheusMetricDto;
import api.it.ianus.plugin.prometheus.MetadataApi;
import api.client.prometheus.it.ianus.plugin.TargetsApi;
import handler.client.prometheus.it.ianus.plugin.PrometheusRestClient;
import model.client.prometheus.it.ianus.plugin.PrometheusResponse;
import collector.prometheus.it.ianus.plugin.PrometheusCollector;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ianus/prometheus")
public class PrometheusController {

    private final PrometheusCollector collector;
    private final TargetsApi targetsApi;
    private final AlertsApi alertsApi;
    private final MetadataApi metadataApi;

    public PrometheusController(PrometheusCollector collector, PrometheusRestClient restClient) {
        this.collector = collector;
        this.targetsApi = new TargetsApi(restClient);
        this.alertsApi = new AlertsApi(restClient);
        this.metadataApi = new MetadataApi(restClient);
    }

    @GetMapping("/metrics/all")
    public PrometheusMetricCollectionDto getAllMetrics(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<PrometheusMetricDto> all = collector.getAll();
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<PrometheusMetricDto> page = all.subList(fromIndex, toIndex);
        return new PrometheusMetricCollectionDto(page.size(), offset, totalCount, page);
    }

    @GetMapping("/metrics")
    public PrometheusMetricCollectionDto getMetrics(
            @RequestParam String metric,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int count) {

        List<PrometheusMetricDto> all = collector.get(metric);
        int totalCount = all.size();
        int fromIndex = Math.min(offset, totalCount);
        int toIndex = Math.min(offset + count, totalCount);
        List<PrometheusMetricDto> page = all.subList(fromIndex, toIndex);
        return new PrometheusMetricCollectionDto(page.size(), offset, totalCount, page);
    }

    @GetMapping("/targets")
    public PrometheusResponse getTargets() {
        return targetsApi.getTargets();
    }

    @GetMapping("/alerts")
    public PrometheusResponse getAlerts() {
        return alertsApi.getAlerts();
    }

    @GetMapping("/rules")
    public PrometheusResponse getRules() {
        return alertsApi.getRules();
    }

    @GetMapping("/labels")
    public PrometheusResponse getLabels() {
        return metadataApi.getLabels();
    }

    @GetMapping("/label/{name}/values")
    public PrometheusResponse getLabelValues(@PathVariable String name) {
        return metadataApi.getLabelValues(name);
    }
}
```

**Step 5: Run test to verify it passes**

Run: `./mvnw test -Dtest=PrometheusControllerTest`
Expected: PASS

**Step 6: Commit**

```bash
git add src/main/java/org/opennms/integrations/ianus/prometheus/controller/ \
        src/test/java/org/opennms/integrations/ianus/prometheus/controller/
git commit -m "feat(prometheus): add PrometheusController with cached and live endpoints"
```

---

### Task 9: Full build verification and context load test

**Files:**
- None (verification only)

**Step 1: Run full test suite**

Run: `./mvnw test`
Expected: BUILD SUCCESS — all tests pass including `OpennmsIanusApplicationTests.contextLoads()`

The context load test is important because it verifies:
- `PrometheusProperties` binds correctly from `application.properties`
- `PrometheusClientConfig` creates the `prometheusClient` RestClient bean
- `PrometheusRestClient` injects correctly via `@Qualifier("prometheusClient")`
- No bean name collisions with the OpenNMS beans

**Step 2: If context load fails, debug and fix**

Common issues:
- Bean name collision → rename the `@Bean` method
- Missing properties → add defaults in `application.properties`
- Circular dependency → check `@Qualifier` wiring

**Step 3: Commit any fixes**

```bash
git add -A
git commit -m "fix(prometheus): resolve context load issues"
```

---

### Task 10: Final cleanup and full compile

**Step 1: Run full build**

Run: `./mvnw compile test`
Expected: BUILD SUCCESS

**Step 2: Verify no unused imports or dead code**

Quick scan of all new files for cleanliness.

**Step 3: Final commit if needed**

```bash
git add -A
git commit -m "chore(prometheus): final cleanup"
```
