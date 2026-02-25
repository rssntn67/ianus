package org.opennms.integrations.ianus.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
/**
 * QueryNode
 */

public class QueryNode {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("foreignSource")
  private String foreignSource = null;

  @JsonProperty("foreignId")
  private String foreignId = null;

  @JsonProperty("label")
  private String label = null;

  public QueryNode id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public QueryNode foreignSource(String foreignSource) {
    this.foreignSource = foreignSource;
    return this;
  }

   /**
   * Get foreignSource
   * @return foreignSource
  **/
  public String getForeignSource() {
    return foreignSource;
  }

  public void setForeignSource(String foreignSource) {
    this.foreignSource = foreignSource;
  }

  public QueryNode foreignId(String foreignId) {
    this.foreignId = foreignId;
    return this;
  }

   /**
   * Get foreignId
   * @return foreignId
  **/
  public String getForeignId() {
    return foreignId;
  }

  public void setForeignId(String foreignId) {
    this.foreignId = foreignId;
  }

  public QueryNode label(String label) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryNode queryNode = (QueryNode) o;
    return Objects.equals(this.id, queryNode.id) &&
        Objects.equals(this.foreignSource, queryNode.foreignSource) &&
        Objects.equals(this.foreignId, queryNode.foreignId) &&
        Objects.equals(this.label, queryNode.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, foreignSource, foreignId, label);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryNode {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    foreignSource: ").append(toIndentedString(foreignSource)).append("\n");
    sb.append("    foreignId: ").append(toIndentedString(foreignId)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
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
