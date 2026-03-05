package it.ianus.plugin.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * An active Prometheus scrape target.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveTarget {

    private Map<String, String> labels;
    private String scrapePool;
    private String scrapeUrl;
    private String health;
    private String lastScrape;
    private String lastScrapeDuration;
    private String lastError;

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public String getScrapePool() {
        return scrapePool;
    }

    public void setScrapePool(String scrapePool) {
        this.scrapePool = scrapePool;
    }

    public String getScrapeUrl() {
        return scrapeUrl;
    }

    public void setScrapeUrl(String scrapeUrl) {
        this.scrapeUrl = scrapeUrl;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLastScrape() {
        return lastScrape;
    }

    public void setLastScrape(String lastScrape) {
        this.lastScrape = lastScrape;
    }

    public String getLastScrapeDuration() {
        return lastScrapeDuration;
    }

    public void setLastScrapeDuration(String lastScrapeDuration) {
        this.lastScrapeDuration = lastScrapeDuration;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }
}
