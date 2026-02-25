package org.opennms.integrations.ianus.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WrappedPrimitive
 */
public class WrappedPrimitive {
  @JsonProperty("list")
  private List<Double> list = null;

  public WrappedPrimitive list(List<Double> list) {
    this.list = list;
    return this;
  }

  public WrappedPrimitive addListItem(Double listItem) {
    if (this.list == null) {
      this.list = new ArrayList<>();
    }
    this.list.add(listItem);
    return this;
  }

   /**
   * Get list
   * @return list
  **/
  public List<Double> getList() {
    return list;
  }

  public void setList(List<Double> list) {
    this.list = list;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WrappedPrimitive wrappedPrimitive = (WrappedPrimitive) o;
    return Objects.equals(this.list, wrappedPrimitive.list);
  }

  @Override
  public int hashCode() {
    return Objects.hash(list);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WrappedPrimitive {\n");
    
    sb.append("    list: ").append(toIndentedString(list)).append("\n");
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
