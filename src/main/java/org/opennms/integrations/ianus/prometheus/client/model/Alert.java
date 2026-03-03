package org.opennms.integrations.ianus.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * A Prometheus alert.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alert {

    private Map<String, String> labels;
    private Map<String, String> annotations;
    private String state;
    private String activeAt;
    private String value;

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public Map<String, String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Map<String, String> annotations) {
        this.annotations = annotations;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getActiveAt() {
        return activeAt;
    }

    public void setActiveAt(String activeAt) {
        this.activeAt = activeAt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
