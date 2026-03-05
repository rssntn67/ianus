package it.ianus.plugin.clients.opennms.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opennms")
public record OpenNMSProperties(
        String baseUrl,
        String username,
        String password,
        Duration connectTimeout,
        Duration readTimeout) {

    public OpenNMSProperties {
        if (connectTimeout == null) connectTimeout = Duration.ofSeconds(5);
        if (readTimeout == null) readTimeout = Duration.ofSeconds(30);
    }
}
