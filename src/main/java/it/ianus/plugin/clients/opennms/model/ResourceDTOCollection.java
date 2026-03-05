package it.ianus.plugin.clients.opennms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ResourceDTOCollection
 */
public class ResourceDTOCollection {
  @JsonProperty("resource")
  private List<ResourceDTO> objects = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  @JsonProperty("count")
  private Integer count = null;

  @JsonProperty("offset")
  private Integer offset = null;

  public ResourceDTOCollection objects(List<ResourceDTO> objects) {
    this.objects = objects;
    return this;
  }

  public ResourceDTOCollection addObjectsItem(ResourceDTO objectsItem) {
    if (this.objects == null) {
      this.objects = new ArrayList<>();
    }
    this.objects.add(objectsItem);
    return this;
  }

   /**
   * Get objects
   * @return objects
  **/
  public List<ResourceDTO> getObjects() {
    return objects;
  }

  public void setObjects(List<ResourceDTO> objects) {
    this.objects = objects;
  }

  public ResourceDTOCollection totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

   /**
   * Get totalCount
   * @return totalCount
  **/
  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public ResourceDTOCollection count(Integer count) {
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public ResourceDTOCollection offset(Integer offset) {
    this.offset = offset;
    return this;
  }

   /**
   * Get offset
   * @return offset
  **/
  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceDTOCollection resourceDTOCollection = (ResourceDTOCollection) o;
    return Objects.equals(this.objects, resourceDTOCollection.objects) &&
        Objects.equals(this.totalCount, resourceDTOCollection.totalCount) &&
        Objects.equals(this.count, resourceDTOCollection.count) &&
        Objects.equals(this.offset, resourceDTOCollection.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objects, totalCount, count, offset);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceDTOCollection {\n");
    
    sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
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
