# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
./mvnw compile                  # compile
./mvnw test                     # run all tests (context-load test)
./mvnw spring-boot:run          # run the application
./mvnw test -Dtest=ClassName    # run a single test class
```

## Architecture

Spring Boot 4.0.3 / Java 21 application that acts as an integration layer over external monitoring APIs (OpenNMS, Prometheus).

### Package layout

```
org.opennms.integrations.ianus
├── OpennmsIanusApplication.java        # @SpringBootApplication entry point
├── OpenNmsProperties.java              # @ConfigurationProperties(prefix="opennms") record
├── OpenNmsClientConfig.java            # @Configuration — produces RestClient bean "openNmsClient"
└── client/
    ├── api/
    │   ├── ResourcesApi.java           # Typed calls to /resources endpoints
    │   └── MeasumentsApi.java          # Typed calls to /measurements endpoints
    ├── handler/
    │   ├── OpenNmsRestClient.java      # @Service — get/post helpers wrapping RestClient
    │   └── OpenNmsClientException.java # RuntimeException wrapping RestClientResponseException
    └── model/                          # Jackson-annotated POJOs for OpenNMS REST API
        └── (QueryRequest, QueryResponse, ResourceDTO, ...)
```

### Prometheus plugin (`org.opennms.integrations.ianus.prometheus`)

```
prometheus/
├── PrometheusProperties.java        # @ConfigurationProperties(prefix="prometheus") record
├── PrometheusClientConfig.java      # @Configuration — produces RestClient bean "prometheusClient"
├── client/                          # Same structure as OpenNMS client
│   ├── api/                         # QueryApi, MetadataApi, TargetsApi, AlertsApi
│   ├── handler/                     # PrometheusRestClient @Service, PrometheusClientException
│   └── model/                       # Jackson POJOs for Prometheus API responses
├── collector/
│   └── PrometheusCollector.java     # @Scheduled metric polling, ConcurrentHashMap cache
└── controller/
    ├── PrometheusController.java    # 7 REST endpoints at /ianus/prometheus/
    └── *Dto.java                    # PrometheusMetricDto, PrometheusMetricCollectionDto
```

### Key design decisions

- `OpenNmsClientConfig` names its `@Bean` method `openNmsClient` (not `openNmsRestClient`) to avoid a Spring bean-name collision with the `@Service` class `OpenNmsRestClient` (Spring lowercases the class name to derive a default bean name).
- `OpenNmsRestClient` injects the `RestClient` via `@Qualifier("openNmsClient")`.
- The `RestClient` is pre-configured with Basic Auth, base URL, timeouts, and `Accept: application/json`.
- `ResourcesApi` and `MeasumentsApi` are plain classes (not Spring beans) — callers must instantiate them with an `OpenNmsRestClient`.

### Adding a new integration plugin

Follow the pattern established by the OpenNMS and Prometheus modules:
1. `*Properties.java` — `@ConfigurationProperties` record for config
2. `*ClientConfig.java` — `@Configuration` producing a named `RestClient` bean (name must differ from service class)
3. `client/handler/*RestClient.java` — `@Service` with `@Qualifier` injection, generic get/post helpers
4. `client/api/*.java` — plain classes (not beans), instantiated with the rest client
5. `client/model/*.java` — Jackson POJOs

### Configuration (`application.properties`)

```properties
opennms.base-url=http://localhost:8980/opennms/rest
opennms.username=admin
opennms.password=admin
# opennms.connect-timeout=5s   (default)
# opennms.read-timeout=30s     (default)
```

### Prometheus configuration

```properties
prometheus.base-url=http://localhost:9090/api/v1
# prometheus.username=             (optional)
# prometheus.password=             (optional)
# prometheus.connect-timeout=5s    (default)
# prometheus.read-timeout=30s      (default)
# prometheus.collector.interval-ms=300000  (5 min default)
# prometheus.collector.queries=up          (PromQL queries to poll)
```
