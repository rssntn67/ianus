package org.opennms.integrations.ianus;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opennms")
public record OpenNmsProperties(
        String baseUrl,
        String username,
        String password,
        Duration connectTimeout,
        Duration readTimeout) {

    public OpenNmsProperties {
        if (connectTimeout == null) connectTimeout = Duration.ofSeconds(5);
        if (readTimeout == null) readTimeout = Duration.ofSeconds(30);
    }
}
