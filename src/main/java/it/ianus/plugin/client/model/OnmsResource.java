package it.ianus.plugin.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * OnmsResource
 */
public class OnmsResource {
  @JsonProperty("link")
  private String link = null;

  @JsonProperty("rrdGraphAttributes")
  private Map<String, RrdGraphAttribute> rrdGraphAttributes = null;

  @JsonProperty("stringPropertyAttributes")
  private Map<String, String> stringPropertyAttributes = null;

  @JsonProperty("externalValueAttributes")
  private Map<String, String> externalValueAttributes = null;

  @JsonProperty("label")
  private String label = null;

  @JsonProperty("resourceType")
  private OnmsResourceType resourceType = null;

  @JsonProperty("entity")
  private OnmsEntity entity = null;

  @JsonProperty("childResources")
  private List<OnmsResource> childResources = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("parent")
  private OnmsResource parent = null;

  @JsonProperty("id")
  private ResourceId id = null;

  @JsonProperty("path")
  private ResourcePath path = null;

  @JsonProperty("attributes")
  private List<OnmsAttribute> attributes = null;

  public OnmsResource link(String link) {
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

  public OnmsResource rrdGraphAttributes(Map<String, RrdGraphAttribute> rrdGraphAttributes) {
    this.rrdGraphAttributes = rrdGraphAttributes;
    return this;
  }

  public OnmsResource putRrdGraphAttributesItem(String key, RrdGraphAttribute rrdGraphAttributesItem) {
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

  public OnmsResource stringPropertyAttributes(Map<String, String> stringPropertyAttributes) {
    this.stringPropertyAttributes = stringPropertyAttributes;
    return this;
  }

  public OnmsResource putStringPropertyAttributesItem(String key, String stringPropertyAttributesItem) {
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

  public OnmsResource externalValueAttributes(Map<String, String> externalValueAttributes) {
    this.externalValueAttributes = externalValueAttributes;
    return this;
  }

  public OnmsResource putExternalValueAttributesItem(String key, String externalValueAttributesItem) {
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

  public OnmsResource label(String label) {
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

  public OnmsResource resourceType(OnmsResourceType resourceType) {
    this.resourceType = resourceType;
    return this;
  }

   /**
   * Get resourceType
   * @return resourceType
  **/
  public OnmsResourceType getResourceType() {
    return resourceType;
  }

  public void setResourceType(OnmsResourceType resourceType) {
    this.resourceType = resourceType;
  }

  public OnmsResource entity(OnmsEntity entity) {
    this.entity = entity;
    return this;
  }

   /**
   * Get entity
   * @return entity
  **/
  public OnmsEntity getEntity() {
    return entity;
  }

  public void setEntity(OnmsEntity entity) {
    this.entity = entity;
  }

  public OnmsResource childResources(List<OnmsResource> childResources) {
    this.childResources = childResources;
    return this;
  }

  public OnmsResource addChildResourcesItem(OnmsResource childResourcesItem) {
    if (this.childResources == null) {
      this.childResources = new ArrayList<>();
    }
    this.childResources.add(childResourcesItem);
    return this;
  }

   /**
   * Get childResources
   * @return childResources
  **/
  public List<OnmsResource> getChildResources() {
    return childResources;
  }

  public void setChildResources(List<OnmsResource> childResources) {
    this.childResources = childResources;
  }

  public OnmsResource name(String name) {
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

  public OnmsResource parent(OnmsResource parent) {
    this.parent = parent;
    return this;
  }

   /**
   * Get parent
   * @return parent
  **/
  public OnmsResource getParent() {
    return parent;
  }

  public void setParent(OnmsResource parent) {
    this.parent = parent;
  }

  public OnmsResource id(ResourceId id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  public ResourceId getId() {
    return id;
  }

  public void setId(ResourceId id) {
    this.id = id;
  }

  public OnmsResource path(ResourcePath path) {
    this.path = path;
    return this;
  }

   /**
   * Get path
   * @return path
  **/
  public ResourcePath getPath() {
    return path;
  }

  public void setPath(ResourcePath path) {
    this.path = path;
  }

  public OnmsResource attributes(List<OnmsAttribute> attributes) {
    this.attributes = attributes;
    return this;
  }

  public OnmsResource addAttributesItem(OnmsAttribute attributesItem) {
    if (this.attributes == null) {
      this.attributes = new ArrayList<>();
    }
    this.attributes.add(attributesItem);
    return this;
  }

   /**
   * Get attributes
   * @return attributes
  **/
  public List<OnmsAttribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<OnmsAttribute> attributes) {
    this.attributes = attributes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OnmsResource onmsResource = (OnmsResource) o;
    return Objects.equals(this.link, onmsResource.link) &&
        Objects.equals(this.rrdGraphAttributes, onmsResource.rrdGraphAttributes) &&
        Objects.equals(this.stringPropertyAttributes, onmsResource.stringPropertyAttributes) &&
        Objects.equals(this.externalValueAttributes, onmsResource.externalValueAttributes) &&
        Objects.equals(this.label, onmsResource.label) &&
        Objects.equals(this.resourceType, onmsResource.resourceType) &&
        Objects.equals(this.entity, onmsResource.entity) &&
        Objects.equals(this.childResources, onmsResource.childResources) &&
        Objects.equals(this.name, onmsResource.name) &&
        Objects.equals(this.parent, onmsResource.parent) &&
        Objects.equals(this.id, onmsResource.id) &&
        Objects.equals(this.path, onmsResource.path) &&
        Objects.equals(this.attributes, onmsResource.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(link, rrdGraphAttributes, stringPropertyAttributes, externalValueAttributes, label, resourceType, entity, childResources, name, parent, id, path, attributes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OnmsResource {\n");
    
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    rrdGraphAttributes: ").append(toIndentedString(rrdGraphAttributes)).append("\n");
    sb.append("    stringPropertyAttributes: ").append(toIndentedString(stringPropertyAttributes)).append("\n");
    sb.append("    externalValueAttributes: ").append(toIndentedString(externalValueAttributes)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
    sb.append("    entity: ").append(toIndentedString(entity)).append("\n");
    sb.append("    childResources: ").append(toIndentedString(childResources)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
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
