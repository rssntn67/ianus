package org.opennms.integrations.ianus.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OnmsCategory
 */
public class OnmsCategory {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("authorizedGroups")
  private List<String> authorizedGroups = null;

  @JsonProperty("name")
  private String name = null;

  public OnmsCategory id(Integer id) {
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

  public OnmsCategory description(String description) {
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

  public OnmsCategory authorizedGroups(List<String> authorizedGroups) {
    this.authorizedGroups = authorizedGroups;
    return this;
  }

  public OnmsCategory addAuthorizedGroupsItem(String authorizedGroupsItem) {
    if (this.authorizedGroups == null) {
      this.authorizedGroups = new ArrayList<>();
    }
    this.authorizedGroups.add(authorizedGroupsItem);
    return this;
  }

   /**
   * Get authorizedGroups
   * @return authorizedGroups
  **/
 
  public List<String> getAuthorizedGroups() {
    return authorizedGroups;
  }

  public void setAuthorizedGroups(List<String> authorizedGroups) {
    this.authorizedGroups = authorizedGroups;
  }

  public OnmsCategory name(String name) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OnmsCategory onmsCategory = (OnmsCategory) o;
    return Objects.equals(this.id, onmsCategory.id) &&
        Objects.equals(this.description, onmsCategory.description) &&
        Objects.equals(this.authorizedGroups, onmsCategory.authorizedGroups) &&
        Objects.equals(this.name, onmsCategory.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, authorizedGroups, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OnmsCategory {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    authorizedGroups: ").append(toIndentedString(authorizedGroups)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
