package it.ianus.plugin.prometheus.client.handler;

public class PrometheusClientException extends RuntimeException {

    public PrometheusClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
