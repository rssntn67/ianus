package it.ianus.plugin.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * A dropped Prometheus scrape target.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DroppedTarget {

    private Map<String, String> discoveredLabels;

    public Map<String, String> getDiscoveredLabels() {
        return discoveredLabels;
    }

    public void setDiscoveredLabels(Map<String, String> discoveredLabels) {
        this.discoveredLabels = discoveredLabels;
    }
}
