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

Spring Boot 4.0.3 / Java 21 application that acts as an integration layer over the OpenNMS REST API.

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

### Key design decisions

- `OpenNmsClientConfig` names its `@Bean` method `openNmsClient` (not `openNmsRestClient`) to avoid a Spring bean-name collision with the `@Service` class `OpenNmsRestClient` (Spring lowercases the class name to derive a default bean name).
- `OpenNmsRestClient` injects the `RestClient` via `@Qualifier("openNmsClient")`.
- The `RestClient` is pre-configured with Basic Auth, base URL, timeouts, and `Accept: application/json`.
- `ResourcesApi` and `MeasumentsApi` are plain classes (not Spring beans) — callers must instantiate them with an `OpenNmsRestClient`.

### Configuration (`application.properties`)

```properties
opennms.base-url=http://localhost:8980/opennms/rest
opennms.username=admin
opennms.password=admin
# opennms.connect-timeout=5s   (default)
# opennms.read-timeout=30s     (default)
```