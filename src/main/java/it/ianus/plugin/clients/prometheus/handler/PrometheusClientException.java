package it.ianus.plugin.clients.prometheus.handler;

public class PrometheusClientException extends RuntimeException {

    public PrometheusClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
