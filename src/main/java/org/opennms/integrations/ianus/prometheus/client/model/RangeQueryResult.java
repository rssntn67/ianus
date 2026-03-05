package org.opennms.integrations.ianus.prometheus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Result of /api/v1/query_range.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RangeQueryResult {

    private String resultType;
    private List<MatrixSample> result;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<MatrixSample> getResult() {
        return result;
    }

    public void setResult(List<MatrixSample> result) {
        this.result = result;
    }
}
