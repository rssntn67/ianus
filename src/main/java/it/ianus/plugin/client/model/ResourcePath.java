package it.ianus.plugin.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * ResourcePath
 */
public class ResourcePath {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("parent")
  private ResourcePath parent = null;

  public ResourcePath name(String name) {
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

  public ResourcePath parent(ResourcePath parent) {
    this.parent = parent;
    return this;
  }

   /**
   * Get parent
   * @return parent
  **/
  public ResourcePath getParent() {
    return parent;
  }

  public void setParent(ResourcePath parent) {
    this.parent = parent;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourcePath resourcePath = (ResourcePath) o;
    return Objects.equals(this.name, resourcePath.name) &&
        Objects.equals(this.parent, resourcePath.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourcePath {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
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
