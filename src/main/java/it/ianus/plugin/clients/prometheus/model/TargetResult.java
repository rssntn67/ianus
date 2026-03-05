package it.ianus.plugin.clients.prometheus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Result of /api/v1/targets.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetResult {

    private List<ActiveTarget> activeTargets;
    private List<DroppedTarget> droppedTargets;

    public List<ActiveTarget> getActiveTargets() {
        return activeTargets;
    }

    public void setActiveTargets(List<ActiveTarget> activeTargets) {
        this.activeTargets = activeTargets;
    }

    public List<DroppedTarget> getDroppedTargets() {
        return droppedTargets;
    }

    public void setDroppedTargets(List<DroppedTarget> droppedTargets) {
        this.droppedTargets = droppedTargets;
    }
}
