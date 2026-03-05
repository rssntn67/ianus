package it.ianus.plugin.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Single instant query result element.
 * The value field is [unix_timestamp_double, "string_value"].
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VectorSample {

    private Map<String, String> metric;
    private List<Object> value;

    public Map<String, String> getMetric() {
        return metric;
    }

    public void setMetric(Map<String, String> metric) {
        this.metric = metric;
    }

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }
}
