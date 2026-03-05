package it.ianus.plugin.clients.opennms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * FilterMetaData
 */

public class FilterMetaData {
  @JsonProperty("canonicalName")
  private String canonicalName = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("backend")
  private String backend = null;

  @JsonProperty("parameters")
  private List<FilterParamMetaData> parameters = null;

  public FilterMetaData canonicalName(String canonicalName) {
    this.canonicalName = canonicalName;
    return this;
  }

   /**
   * Get canonicalName
   * @return canonicalName
  **/
  public String getCanonicalName() {
    return canonicalName;
  }

  public void setCanonicalName(String canonicalName) {
    this.canonicalName = canonicalName;
  }

  public FilterMetaData name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FilterMetaData description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public FilterMetaData backend(String backend) {
    this.backend = backend;
    return this;
  }

   /**
   * Get backend
   * @return backend
  **/
  public String getBackend() {
    return backend;
  }

  public void setBackend(String backend) {
    this.backend = backend;
  }

  public FilterMetaData parameters(List<FilterParamMetaData> parameters) {
    this.parameters = parameters;
    return this;
  }

  public FilterMetaData addParametersItem(FilterParamMetaData parametersItem) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<>();
    }
    this.parameters.add(parametersItem);
    return this;
  }

   /**
   * Get parameters
   * @return parameters
  **/
  public List<FilterParamMetaData> getParameters() {
    return parameters;
  }

  public void setParameters(List<FilterParamMetaData> parameters) {
    this.parameters = parameters;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FilterMetaData filterMetaData = (FilterMetaData) o;
    return Objects.equals(this.canonicalName, filterMetaData.canonicalName) &&
        Objects.equals(this.name, filterMetaData.name) &&
        Objects.equals(this.description, filterMetaData.description) &&
        Objects.equals(this.backend, filterMetaData.backend) &&
        Objects.equals(this.parameters, filterMetaData.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canonicalName, name, description, backend, parameters);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FilterMetaData {\n");
    
    sb.append("    canonicalName: ").append(toIndentedString(canonicalName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    backend: ").append(toIndentedString(backend)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
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
