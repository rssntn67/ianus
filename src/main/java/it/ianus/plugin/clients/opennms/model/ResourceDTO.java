package it.ianus.plugin.clients.opennms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ResourceDTO
 */
public class ResourceDTO {
  @JsonProperty("typeLabel")
  private String typeLabel = null;

  @JsonProperty("graphNames")
  private List<String> graphNames = null;

  @JsonProperty("rrdGraphAttributes")
  private Map<String, RrdGraphAttribute> rrdGraphAttributes = null;

  @JsonProperty("stringPropertyAttributes")
  private Map<String, String> stringPropertyAttributes = null;

  @JsonProperty("externalValueAttributes")
  private Map<String, String> externalValueAttributes = null;

  @JsonProperty("link")
  private String link = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resource")
  private OnmsResource resource = null;

  @JsonProperty("label")
  private String label = null;

  @JsonProperty("parentId")
  private String parentId = null;

  @JsonProperty("children")
  private ResourceDTOCollection children = null;

  @JsonProperty("name")
  private String name = null;

  public ResourceDTO typeLabel(String typeLabel) {
    this.typeLabel = typeLabel;
    return this;
  }

   /**
   * Get typeLabel
   * @return typeLabel
  **/
  public String getTypeLabel() {
    return typeLabel;
  }

  public void setTypeLabel(String typeLabel) {
    this.typeLabel = typeLabel;
  }

  public ResourceDTO graphNames(List<String> graphNames) {
    this.graphNames = graphNames;
    return this;
  }

  public ResourceDTO addGraphNamesItem(String graphNamesItem) {
    if (this.graphNames == null) {
      this.graphNames = new ArrayList<>();
    }
    this.graphNames.add(graphNamesItem);
    return this;
  }

   /**
   * Get graphNames
   * @return graphNames
  **/
  public List<String> getGraphNames() {
    return graphNames;
  }

  public void setGraphNames(List<String> graphNames) {
    this.graphNames = graphNames;
  }

  public ResourceDTO rrdGraphAttributes(Map<String, RrdGraphAttribute> rrdGraphAttributes) {
    this.rrdGraphAttributes = rrdGraphAttributes;
    return this;
  }

  public ResourceDTO putRrdGraphAttributesItem(String key, RrdGraphAttribute rrdGraphAttributesItem) {
    if (this.rrdGraphAttributes == null) {
      this.rrdGraphAttributes = new HashMap<>();
    }
    this.rrdGraphAttributes.put(key, rrdGraphAttributesItem);
    return this;
  }

   /**
   * Get rrdGraphAttributes
   * @return rrdGraphAttributes
  **/
  public Map<String, RrdGraphAttribute> getRrdGraphAttributes() {
    return rrdGraphAttributes;
  }

  public void setRrdGraphAttributes(Map<String, RrdGraphAttribute> rrdGraphAttributes) {
    this.rrdGraphAttributes = rrdGraphAttributes;
  }

  public ResourceDTO stringPropertyAttributes(Map<String, String> stringPropertyAttributes) {
    this.stringPropertyAttributes = stringPropertyAttributes;
    return this;
  }

  public ResourceDTO putStringPropertyAttributesItem(String key, String stringPropertyAttributesItem) {
    if (this.stringPropertyAttributes == null) {
      this.stringPropertyAttributes = new HashMap<>();
    }
    this.stringPropertyAttributes.put(key, stringPropertyAttributesItem);
    return this;
  }

   /**
   * Get stringPropertyAttributes
   * @return stringPropertyAttributes
  **/
  public Map<String, String> getStringPropertyAttributes() {
    return stringPropertyAttributes;
  }

  public void setStringPropertyAttributes(Map<String, String> stringPropertyAttributes) {
    this.stringPropertyAttributes = stringPropertyAttributes;
  }

  public ResourceDTO externalValueAttributes(Map<String, String> externalValueAttributes) {
    this.externalValueAttributes = externalValueAttributes;
    return this;
  }

  public ResourceDTO putExternalValueAttributesItem(String key, String externalValueAttributesItem) {
    if (this.externalValueAttributes == null) {
      this.externalValueAttributes = new HashMap<>();
    }
    this.externalValueAttributes.put(key, externalValueAttributesItem);
    return this;
  }

   /**
   * Get externalValueAttributes
   * @return externalValueAttributes
  **/
  public Map<String, String> getExternalValueAttributes() {
    return externalValueAttributes;
  }

  public void setExternalValueAttributes(Map<String, String> externalValueAttributes) {
    this.externalValueAttributes = externalValueAttributes;
  }

  public ResourceDTO link(String link) {
    this.link = link;
    return this;
  }

   /**
   * Get link
   * @return link
  **/
  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public ResourceDTO id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ResourceDTO resource(OnmsResource resource) {
    this.resource = resource;
    return this;
  }

   /**
   * Get resource
   * @return resource
  **/
  public OnmsResource getResource() {
    return resource;
  }

  public void setResource(OnmsResource resource) {
    this.resource = resource;
  }

  public ResourceDTO label(String label) {
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

  public ResourceDTO parentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

   /**
   * Get parentId
   * @return parentId
  **/
  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public ResourceDTO children(ResourceDTOCollection children) {
    this.children = children;
    return this;
  }

   /**
   * Get children
   * @return children
  **/
  public ResourceDTOCollection getChildren() {
    return children;
  }

  public void setChildren(ResourceDTOCollection children) {
    this.children = children;
  }

  public ResourceDTO name(String name) {
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
    ResourceDTO resourceDTO = (ResourceDTO) o;
    return Objects.equals(this.typeLabel, resourceDTO.typeLabel) &&
        Objects.equals(this.graphNames, resourceDTO.graphNames) &&
        Objects.equals(this.rrdGraphAttributes, resourceDTO.rrdGraphAttributes) &&
        Objects.equals(this.stringPropertyAttributes, resourceDTO.stringPropertyAttributes) &&
        Objects.equals(this.externalValueAttributes, resourceDTO.externalValueAttributes) &&
        Objects.equals(this.link, resourceDTO.link) &&
        Objects.equals(this.id, resourceDTO.id) &&
        Objects.equals(this.resource, resourceDTO.resource) &&
        Objects.equals(this.label, resourceDTO.label) &&
        Objects.equals(this.parentId, resourceDTO.parentId) &&
        Objects.equals(this.children, resourceDTO.children) &&
        Objects.equals(this.name, resourceDTO.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeLabel, graphNames, rrdGraphAttributes, stringPropertyAttributes, externalValueAttributes, link, id, resource, label, parentId, children, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceDTO {\n");
    
    sb.append("    typeLabel: ").append(toIndentedString(typeLabel)).append("\n");
    sb.append("    graphNames: ").append(toIndentedString(graphNames)).append("\n");
    sb.append("    rrdGraphAttributes: ").append(toIndentedString(rrdGraphAttributes)).append("\n");
    sb.append("    stringPropertyAttributes: ").append(toIndentedString(stringPropertyAttributes)).append("\n");
    sb.append("    externalValueAttributes: ").append(toIndentedString(externalValueAttributes)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    children: ").append(toIndentedString(children)).append("\n");
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
