package it.ianus.plugin.clients.prometheus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Result of /api/v1/alerts.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertResult {

    private List<Alert> alerts;

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
