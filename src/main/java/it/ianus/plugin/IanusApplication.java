package it.ianus.plugin;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({IanusApplication.OpenNMSProperties.class, IanusApplication.PrometheusProperties.class})
public class IanusApplication {

    public static void main(String[] args) {
        SpringApplication.run(IanusApplication.class, args);
    }

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

    @Bean
    RestClient openNmsClient(OpenNMSProperties props) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout((int) props.connectTimeout().toMillis());
        factory.setReadTimeout((int) props.readTimeout().toMillis());

        String credentials = props.username() + ":" + props.password();
        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        return RestClient.builder()
                .baseUrl(props.baseUrl())
                .requestFactory(factory)
                .defaultHeader(HttpHeaders.AUTHORIZATION, basicAuth)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

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
