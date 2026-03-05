package it.ianus.plugin.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Result of /api/v1/rules.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleGroupResult {

    private List<RuleGroup> groups;

    public List<RuleGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<RuleGroup> groups) {
        this.groups = groups;
    }
}
