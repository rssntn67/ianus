package it.ianus.plugin.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Result of /api/v1/query.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstantQueryResult {

    private String resultType;
    private List<VectorSample> result;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<VectorSample> getResult() {
        return result;
    }

    public void setResult(List<VectorSample> result) {
        this.result = result;
    }
}
