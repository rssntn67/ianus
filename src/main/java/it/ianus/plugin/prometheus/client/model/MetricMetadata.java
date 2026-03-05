package it.ianus.plugin.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Single metadata entry.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricMetadata {

    private String type;
    private String help;
    private String unit;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
