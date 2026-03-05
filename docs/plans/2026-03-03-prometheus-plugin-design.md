# Prometheus Plugin Design

## Overview

Add a Prometheus integration plugin to Ianus that reads metrics, targets, alerts, rules, and labels from a Prometheus HTTP API. Follows the same 4-layer architecture as the existing OpenNMS plugin.

## Configuration

```properties
prometheus.base-url=http://your-prometheus:9090/api/v1
prometheus.username=          # optional — enables Basic Auth when present
prometheus.password=          # optional
prometheus.connect-timeout=5s
prometheus.read-timeout=30s
prometheus.collector.interval-ms=300000
prometheus.collector.queries=up,node_cpu_seconds_total
```

`PrometheusProperties` is a `@ConfigurationProperties(prefix="prometheus")` record. Authentication is optional: if `username` is set, Basic Auth is applied to the RestClient; otherwise no auth.

## Package Structure

```
org.opennms.integrations.ianus.prometheus/
├── PrometheusProperties.java
├── PrometheusClientConfig.java        # @Configuration → "prometheusClient" RestClient bean
├── client/
│   ├── handler/
│   │   ├── PrometheusRestClient.java  # @Service — generic get/post helpers
│   │   └── PrometheusClientException.java
│   ├── api/
│   │   ├── QueryApi.java             # /query, /query_range
│   │   ├── MetadataApi.java          # /metadata, /labels, /label/{name}/values, /series
│   │   ├── TargetsApi.java           # /targets
│   │   └── AlertsApi.java            # /alerts, /rules
│   └── model/
│       ├── PrometheusResponse.java   # Generic wrapper {status, data, errorType, error}
│       ├── InstantQueryResult.java
│       ├── RangeQueryResult.java
│       ├── VectorSample.java
│       ├── MatrixSample.java
│       ├── MetricMetadata.java
│       ├── TargetResult.java
│       ├── ActiveTarget.java
│       ├── DroppedTarget.java
│       ├── AlertResult.java
│       ├── Alert.java
│       ├── RuleGroupResult.java
│       ├── RuleGroup.java
│       └── Rule.java
├── collector/
│   └── PrometheusCollector.java       # @Service @Scheduled — polls and caches metrics
└── controller/
    ├── PrometheusController.java      # @RestController /ianus/prometheus/*
    ├── PrometheusMetricDto.java
    └── PrometheusMetricCollectionDto.java
```

## Client Layer

### PrometheusRestClient (@Service)

Same pattern as `OpenNmsRestClient`: injects `RestClient` via `@Qualifier("prometheusClient")`, provides `get(uri, type)` and `post(uri, body, type)` methods, wraps `RestClientResponseException` in `PrometheusClientException`.

### API Classes (plain classes, not Spring beans)

| Class | Prometheus Endpoints | Methods |
|-------|---------------------|---------|
| `QueryApi` | `/query`, `/query_range` | `instantQuery(promql)`, `rangeQuery(promql, start, end, step)` |
| `MetadataApi` | `/metadata`, `/labels`, `/label/{name}/values`, `/series` | `getMetadata()`, `getLabels()`, `getLabelValues(name)`, `getSeries(matchers)` |
| `TargetsApi` | `/targets` | `getTargets()` |
| `AlertsApi` | `/alerts`, `/rules` | `getAlerts()`, `getRules()` |

### Models

All Prometheus API responses share a common envelope:

```java
record PrometheusResponse<T>(String status, T data, String errorType, String error) {}
```

Specific result types:
- `InstantQueryResult`: resultType + List<VectorSample> where VectorSample = metric labels map + [timestamp, value]
- `RangeQueryResult`: resultType + List<MatrixSample> where MatrixSample = metric labels map + values[][timestamp, value]
- `MetricMetadata`: type, help, unit
- `TargetResult`: activeTargets (list of ActiveTarget) + droppedTargets (list of DroppedTarget)
- `AlertResult`: list of Alert (labels, annotations, state, activeAt, value)
- `RuleGroupResult`: list of RuleGroup (name, file, rules)

## Collector

`PrometheusCollector` (@Service):
- Runs on `@Scheduled(fixedRateString = "${prometheus.collector.interval-ms:300000}")`
- Iterates over `prometheus.collector.queries` list
- For each PromQL expression, calls `QueryApi.instantQuery()`
- Stores results in `ConcurrentHashMap<String, List<PrometheusMetricDto>>`
- Key format: metric name from the query

## Controller

`PrometheusController` (@RestController, @RequestMapping("/ianus/prometheus")):

| Endpoint | Source | Description |
|----------|--------|-------------|
| GET `/metrics/all?offset=0&count=10` | Cache | All cached metrics, paginated |
| GET `/metrics?metric={name}&offset=0&count=10` | Cache | Specific metric from cache |
| GET `/targets` | Live | Scrape targets status |
| GET `/alerts` | Live | Active alerts |
| GET `/rules` | Live | Configured rules |
| GET `/labels` | Live | All label names |
| GET `/label/{name}/values` | Live | Values for a specific label |

### DTOs

```java
record PrometheusMetricDto(long timestamp, String metric, Map<String, String> labels, double value) {}
record PrometheusMetricCollectionDto(int count, int offset, int totalCount, List<PrometheusMetricDto> metrics) {}
```

## Design Decisions

1. **Completely independent from OpenNMS plugin** — own properties, RestClient bean, package tree
2. **Optional Basic Auth** — configured via properties, applied only when username is present
3. **Cache for metrics, live for the rest** — metrics are polled periodically; targets/alerts/rules/labels are fetched on demand
4. **API classes are not Spring beans** — same pattern as OpenNMS, callers instantiate with PrometheusRestClient
5. **Generic PrometheusResponse<T> wrapper** — mirrors Prometheus JSON envelope, simplifies deserialization
