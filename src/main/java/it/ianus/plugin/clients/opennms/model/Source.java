package it.ianus.plugin.clients.opennms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Source
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Source {
  @JsonProperty("label")
  private String label = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("attribute")
  private String attribute = null;

  @JsonProperty("fallbackAttribute")
  private String fallbackAttribute = null;

  @JsonProperty("aggregation")
  private String aggregation = null;

  @JsonProperty("transient")
  private Boolean _transient = null;

  @JsonProperty("dataSource")
  private String dataSource = null;

  public Source label(String label) {
    this.label = label;
    return this;
  }

   /**
   * Get label
   * @return label
  **/
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Source resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

   /**
   * Get resourceId
   * @return resourceId
  **/
  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public Source attribute(String attribute) {
    this.attribute = attribute;
    return this;
  }

   /**
   * Get attribute
   * @return attribute
  **/
  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }

  public Source fallbackAttribute(String fallbackAttribute) {
    this.fallbackAttribute = fallbackAttribute;
    return this;
  }

   /**
   * Get fallbackAttribute
   * @return fallbackAttribute
  **/
  public String getFallbackAttribute() {
    return fallbackAttribute;
  }

  public void setFallbackAttribute(String fallbackAttribute) {
    this.fallbackAttribute = fallbackAttribute;
  }

  public Source aggregation(String aggregation) {
    this.aggregation = aggregation;
    return this;
  }

   /**
   * Get aggregation
   * @return aggregation
  **/
  public String getAggregation() {
    return aggregation;
  }

  public void setAggregation(String aggregation) {
    this.aggregation = aggregation;
  }

  public Source _transient(Boolean _transient) {
    this._transient = _transient;
    return this;
  }

   /**
   * Get _transient
   * @return _transient
  **/
  public Boolean isTransient() {
    return _transient;
  }

  public void setTransient(Boolean _transient) {
    this._transient = _transient;
  }

  public Source dataSource(String dataSource) {
    this.dataSource = dataSource;
    return this;
  }

   /**
   * Get dataSource
   * @return dataSource
  **/
  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Source source = (Source) o;
    return Objects.equals(this.label, source.label) &&
        Objects.equals(this.resourceId, source.resourceId) &&
        Objects.equals(this.attribute, source.attribute) &&
        Objects.equals(this.fallbackAttribute, source.fallbackAttribute) &&
        Objects.equals(this.aggregation, source.aggregation) &&
        Objects.equals(this._transient, source._transient) &&
        Objects.equals(this.dataSource, source.dataSource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, resourceId, attribute, fallbackAttribute, aggregation, _transient, dataSource);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Source {\n");
    
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
    sb.append("    fallbackAttribute: ").append(toIndentedString(fallbackAttribute)).append("\n");
    sb.append("    aggregation: ").append(toIndentedString(aggregation)).append("\n");
    sb.append("    _transient: ").append(toIndentedString(_transient)).append("\n");
    sb.append("    dataSource: ").append(toIndentedString(dataSource)).append("\n");
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
