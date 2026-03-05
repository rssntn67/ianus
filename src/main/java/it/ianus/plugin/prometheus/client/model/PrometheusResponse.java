package it.ianus.plugin.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generic wrapper for all Prometheus API responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrometheusResponse {

    private String status;
    private Object data;
    private String errorType;
    private String error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
