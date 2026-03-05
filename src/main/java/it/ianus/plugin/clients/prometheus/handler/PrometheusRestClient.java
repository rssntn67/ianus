package it.ianus.plugin.clients.prometheus.handler;

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
