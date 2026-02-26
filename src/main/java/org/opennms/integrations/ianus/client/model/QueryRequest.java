package org.opennms.integrations.ianus.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QueryRequest
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryRequest {
  @JsonProperty("start")
  private Long start = null;

  @JsonProperty("end")
  private Long end = null;

  @JsonProperty("step")
  private Long step = null;

  @JsonProperty("interval")
  private Long interval = null;

  @JsonProperty("heartbeat")
  private Long heartbeat = null;

  @JsonProperty("relaxed")
  private Boolean relaxed = null;

  @JsonProperty("sources")
  private List<Source> sources = null;

  @JsonProperty("expressions")
  private List<Expression> expressions = null;

  @JsonProperty("filters")
  private List<FilterDef> filters = null;

  @JsonProperty("maxRows")
  private Integer maxRows = null;

  public QueryRequest start(Long start) {
    this.start = start;
    return this;
  }

   /**
   * Get start
   * @return start
  **/
  public Long getStart() {
    return start;
  }

  public void setStart(Long start) {
    this.start = start;
  }

  public QueryRequest end(Long end) {
    this.end = end;
    return this;
  }

   /**
   * Get end
   * @return end
  **/
  public Long getEnd() {
    return end;
  }

  public void setEnd(Long end) {
    this.end = end;
  }

  public QueryRequest step(Long step) {
    this.step = step;
    return this;
  }

   /**
   * Get step
   * @return step
  **/
  public Long getStep() {
    return step;
  }

  public void setStep(Long step) {
    this.step = step;
  }

  public QueryRequest interval(Long interval) {
    this.interval = interval;
    return this;
  }

   /**
   * Get interval
   * @return interval
  **/
  public Long getInterval() {
    return interval;
  }

  public void setInterval(Long interval) {
    this.interval = interval;
  }

  public QueryRequest heartbeat(Long heartbeat) {
    this.heartbeat = heartbeat;
    return this;
  }

   /**
   * Get heartbeat
   * @return heartbeat
  **/
  public Long getHeartbeat() {
    return heartbeat;
  }

  public void setHeartbeat(Long heartbeat) {
    this.heartbeat = heartbeat;
  }

  public QueryRequest relaxed(Boolean relaxed) {
    this.relaxed = relaxed;
    return this;
  }

   /**
   * Get relaxed
   * @return relaxed
  **/
  public Boolean isRelaxed() {
    return relaxed;
  }

  public void setRelaxed(Boolean relaxed) {
    this.relaxed = relaxed;
  }

  public QueryRequest sources(List<Source> sources) {
    this.sources = sources;
    return this;
  }

  public QueryRequest addSourcesItem(Source sourcesItem) {
    if (this.sources == null) {
      this.sources = new ArrayList<>();
    }
    this.sources.add(sourcesItem);
    return this;
  }

   /**
   * Get sources
   * @return sources
  **/
  public List<Source> getSources() {
    return sources;
  }

  public void setSources(List<Source> sources) {
    this.sources = sources;
  }

  public QueryRequest expressions(List<Expression> expressions) {
    this.expressions = expressions;
    return this;
  }

  public QueryRequest addExpressionsItem(Expression expressionsItem) {
    if (this.expressions == null) {
      this.expressions = new ArrayList<>();
    }
    this.expressions.add(expressionsItem);
    return this;
  }

   /**
   * Get expressions
   * @return expressions
  **/
  public List<Expression> getExpressions() {
    return expressions;
  }

  public void setExpressions(List<Expression> expressions) {
    this.expressions = expressions;
  }

  public QueryRequest filters(List<FilterDef> filters) {
    this.filters = filters;
    return this;
  }

  public QueryRequest addFiltersItem(FilterDef filtersItem) {
    if (this.filters == null) {
      this.filters = new ArrayList<>();
    }
    this.filters.add(filtersItem);
    return this;
  }

   /**
   * Get filters
   * @return filters
  **/
  public List<FilterDef> getFilters() {
    return filters;
  }

  public void setFilters(List<FilterDef> filters) {
    this.filters = filters;
  }

  public QueryRequest maxRows(Integer maxRows) {
    this.maxRows = maxRows;
    return this;
  }

   /**
   * Get maxRows
   * @return maxRows
  **/
  public Integer getMaxRows() {
    return maxRows;
  }

  public void setMaxRows(Integer maxRows) {
    this.maxRows = maxRows;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryRequest queryRequest = (QueryRequest) o;
    return Objects.equals(this.start, queryRequest.start) &&
        Objects.equals(this.end, queryRequest.end) &&
        Objects.equals(this.step, queryRequest.step) &&
        Objects.equals(this.interval, queryRequest.interval) &&
        Objects.equals(this.heartbeat, queryRequest.heartbeat) &&
        Objects.equals(this.relaxed, queryRequest.relaxed) &&
        Objects.equals(this.sources, queryRequest.sources) &&
        Objects.equals(this.expressions, queryRequest.expressions) &&
        Objects.equals(this.filters, queryRequest.filters) &&
        Objects.equals(this.maxRows, queryRequest.maxRows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end, step, interval, heartbeat, relaxed, sources, expressions, filters, maxRows);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryRequest {\n");
    
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    step: ").append(toIndentedString(step)).append("\n");
    sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
    sb.append("    heartbeat: ").append(toIndentedString(heartbeat)).append("\n");
    sb.append("    relaxed: ").append(toIndentedString(relaxed)).append("\n");
    sb.append("    sources: ").append(toIndentedString(sources)).append("\n");
    sb.append("    expressions: ").append(toIndentedString(expressions)).append("\n");
    sb.append("    filters: ").append(toIndentedString(filters)).append("\n");
    sb.append("    maxRows: ").append(toIndentedString(maxRows)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
