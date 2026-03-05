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
