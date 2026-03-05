package it.ianus.plugin.clients.opennms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QueryResponse
 */
public class QueryResponse {
  @JsonProperty("step")
  private Long step = null;

  @JsonProperty("start")
  private Long start = null;

  @JsonProperty("end")
  private Long end = null;

  @JsonProperty("timestamps")
  private List<Long> timestamps = null;

  @JsonProperty("labels")
  private List<String> labels = null;

  @JsonProperty("columns")
  private List<WrappedPrimitive> columns = null;

  @JsonProperty("constants")
  private List<QueryConstant> constants = null;

  @JsonProperty("metadata")
  private QueryMetadata metadata = null;

  public QueryResponse step(Long step) {
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

  public QueryResponse start(Long start) {
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

  public QueryResponse end(Long end) {
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

  public QueryResponse timestamps(List<Long> timestamps) {
    this.timestamps = timestamps;
    return this;
  }

  public QueryResponse addTimestampsItem(Long timestampsItem) {
    if (this.timestamps == null) {
      this.timestamps = new ArrayList<>();
    }
    this.timestamps.add(timestampsItem);
    return this;
  }

   /**
   * Get timestamps
   * @return timestamps
  **/
  public List<Long> getTimestamps() {
    return timestamps;
  }

  public void setTimestamps(List<Long> timestamps) {
    this.timestamps = timestamps;
  }

  public QueryResponse labels(List<String> labels) {
    this.labels = labels;
    return this;
  }

  public QueryResponse addLabelsItem(String labelsItem) {
    if (this.labels == null) {
      this.labels = new ArrayList<>();
    }
    this.labels.add(labelsItem);
    return this;
  }

   /**
   * Get labels
   * @return labels
  **/
  public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
  }

  public QueryResponse columns(List<WrappedPrimitive> columns) {
    this.columns = columns;
    return this;
  }

  public QueryResponse addColumnsItem(WrappedPrimitive columnsItem) {
    if (this.columns == null) {
      this.columns = new ArrayList<>();
    }
    this.columns.add(columnsItem);
    return this;
  }

   /**
   * Get columns
   * @return columns
  **/
  public List<WrappedPrimitive> getColumns() {
    return columns;
  }

  public void setColumns(List<WrappedPrimitive> columns) {
    this.columns = columns;
  }

  public QueryResponse constants(List<QueryConstant> constants) {
    this.constants = constants;
    return this;
  }

  public QueryResponse addConstantsItem(QueryConstant constantsItem) {
    if (this.constants == null) {
      this.constants = new ArrayList<>();
    }
    this.constants.add(constantsItem);
    return this;
  }

   /**
   * Get constants
   * @return constants
  **/
  public List<QueryConstant> getConstants() {
    return constants;
  }

  public void setConstants(List<QueryConstant> constants) {
    this.constants = constants;
  }

  public QueryResponse metadata(QueryMetadata metadata) {
    this.metadata = metadata;
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  public QueryMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(QueryMetadata metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryResponse queryResponse = (QueryResponse) o;
    return Objects.equals(this.step, queryResponse.step) &&
        Objects.equals(this.start, queryResponse.start) &&
        Objects.equals(this.end, queryResponse.end) &&
        Objects.equals(this.timestamps, queryResponse.timestamps) &&
        Objects.equals(this.labels, queryResponse.labels) &&
        Objects.equals(this.columns, queryResponse.columns) &&
        Objects.equals(this.constants, queryResponse.constants) &&
        Objects.equals(this.metadata, queryResponse.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(step, start, end, timestamps, labels, columns, constants, metadata);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryResponse {\n");
    
    sb.append("    step: ").append(toIndentedString(step)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    timestamps: ").append(toIndentedString(timestamps)).append("\n");
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
    sb.append("    columns: ").append(toIndentedString(columns)).append("\n");
    sb.append("    constants: ").append(toIndentedString(constants)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
