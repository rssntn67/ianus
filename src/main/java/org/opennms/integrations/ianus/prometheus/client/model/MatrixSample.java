package org.opennms.integrations.ianus.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Single range query result element.
 * The values field is [[unix_timestamp, "value"], [unix_timestamp, "value"], ...].
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatrixSample {

    private Map<String, String> metric;
    private List<List<Object>> values;

    public Map<String, String> getMetric() {
        return metric;
    }

    public void setMetric(Map<String, String> metric) {
        this.metric = metric;
    }

    public List<List<Object>> getValues() {
        return values;
    }

    public void setValues(List<List<Object>> values) {
        this.values = values;
    }
}
