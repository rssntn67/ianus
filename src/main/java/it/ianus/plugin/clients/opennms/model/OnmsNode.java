
package it.ianus.plugin.clients.opennms.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * OnmsNode
 */

public class OnmsNode {
  @JsonProperty("nodeParentId")
  private Integer nodeParentId = null;

  @JsonProperty("location")
  private OnmsMonitoringLocation location = null;

  @JsonProperty("operatingSystem")
  private String operatingSystem = null;

  @JsonProperty("lastIngressFlow")
  private OffsetDateTime lastIngressFlow = null;

  @JsonProperty("nodeId")
  private String nodeId = null;

  @JsonProperty("foreignSource")
  private String foreignSource = null;

  @JsonProperty("label")
  private String label = null;

  @JsonProperty("foreignId")
  private String foreignId = null;

  @JsonProperty("assetRecord")
  private OnmsAssetRecord assetRecord = null;

  @JsonProperty("categories")
  private List<OnmsCategory> categories = null;

  @JsonProperty("sysContact")
  private String sysContact = null;

  @JsonProperty("sysDescription")
  private String sysDescription = null;

  @JsonProperty("sysLocation")
  private String sysLocation = null;

  @JsonProperty("sysName")
  private String sysName = null;

  @JsonProperty("sysObjectId")
  private String sysObjectId = null;

  /**
   * Gets or Sets labelSource
   */
  public enum LabelSourceEnum {
    U("U"),
    N("N"),
    H("H"),
    S("S"),
    A("A"),
    _U(" ");

    private String value;

    LabelSourceEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static LabelSourceEnum fromValue(String input) {
      for (LabelSourceEnum b : LabelSourceEnum.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("labelSource")
  private LabelSourceEnum labelSource = null;

  @JsonProperty("netBiosName")
  private String netBiosName = null;

  @JsonProperty("netBiosDomain")
  private String netBiosDomain = null;

  @JsonProperty("createTime")
  private OffsetDateTime createTime = null;

  @JsonProperty("lastEgressFlow")
  private OffsetDateTime lastEgressFlow = null;

  @JsonProperty("lastCapsdPoll")
  private OffsetDateTime lastCapsdPoll = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    A("A"),
    D("D"),
    _U(" ");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static TypeEnum fromValue(String input) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("type")
  private TypeEnum type = null;

  public OnmsNode nodeParentId(Integer nodeParentId) {
    this.nodeParentId = nodeParentId;
    return this;
  }

   /**
   * Get nodeParentId
   * @return nodeParentId
  **/
 
  public Integer getNodeParentId() {
    return nodeParentId;
  }

  public void setNodeParentId(Integer nodeParentId) {
    this.nodeParentId = nodeParentId;
  }

  public OnmsNode location(OnmsMonitoringLocation location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
 
  public OnmsMonitoringLocation getLocation() {
    return location;
  }

  public void setLocation(OnmsMonitoringLocation location) {
    this.location = location;
  }

  public OnmsNode operatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
    return this;
  }

   /**
   * Get operatingSystem
   * @return operatingSystem
  **/
 
  public String getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public OnmsNode lastIngressFlow(OffsetDateTime lastIngressFlow) {
    this.lastIngressFlow = lastIngressFlow;
    return this;
  }

   /**
   * Get lastIngressFlow
   * @return lastIngressFlow
  **/
 
  public OffsetDateTime getLastIngressFlow() {
    return lastIngressFlow;
  }

  public void setLastIngressFlow(OffsetDateTime lastIngressFlow) {
    this.lastIngressFlow = lastIngressFlow;
  }

  public OnmsNode nodeId(String nodeId) {
    this.nodeId = nodeId;
    return this;
  }

   /**
   * Get nodeId
   * @return nodeId
  **/
 
  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  public OnmsNode foreignSource(String foreignSource) {
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

  public OnmsNode label(String label) {
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

  public OnmsNode foreignId(String foreignId) {
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

  public OnmsNode assetRecord(OnmsAssetRecord assetRecord) {
    this.assetRecord = assetRecord;
    return this;
  }

   /**
   * Get assetRecord
   * @return assetRecord
  **/
 
  public OnmsAssetRecord getAssetRecord() {
    return assetRecord;
  }

  public void setAssetRecord(OnmsAssetRecord assetRecord) {
    this.assetRecord = assetRecord;
  }

  public OnmsNode categories(List<OnmsCategory> categories) {
    this.categories = categories;
    return this;
  }

  public OnmsNode addCategoriesItem(OnmsCategory categoriesItem) {
    if (this.categories == null) {
      this.categories = new ArrayList<>();
    }
    this.categories.add(categoriesItem);
    return this;
  }

   /**
   * Get categories
   * @return categories
  **/
 
  public List<OnmsCategory> getCategories() {
    return categories;
  }

  public void setCategories(List<OnmsCategory> categories) {
    this.categories = categories;
  }

  public OnmsNode sysContact(String sysContact) {
    this.sysContact = sysContact;
    return this;
  }

   /**
   * Get sysContact
   * @return sysContact
  **/
 
  public String getSysContact() {
    return sysContact;
  }

  public void setSysContact(String sysContact) {
    this.sysContact = sysContact;
  }

  public OnmsNode sysDescription(String sysDescription) {
    this.sysDescription = sysDescription;
    return this;
  }

   /**
   * Get sysDescription
   * @return sysDescription
  **/
 
  public String getSysDescription() {
    return sysDescription;
  }

  public void setSysDescription(String sysDescription) {
    this.sysDescription = sysDescription;
  }

  public OnmsNode sysLocation(String sysLocation) {
    this.sysLocation = sysLocation;
    return this;
  }

   /**
   * Get sysLocation
   * @return sysLocation
  **/
 
  public String getSysLocation() {
    return sysLocation;
  }

  public void setSysLocation(String sysLocation) {
    this.sysLocation = sysLocation;
  }

  public OnmsNode sysName(String sysName) {
    this.sysName = sysName;
    return this;
  }

   /**
   * Get sysName
   * @return sysName
  **/
 
  public String getSysName() {
    return sysName;
  }

  public void setSysName(String sysName) {
    this.sysName = sysName;
  }

  public OnmsNode sysObjectId(String sysObjectId) {
    this.sysObjectId = sysObjectId;
    return this;
  }

   /**
   * Get sysObjectId
   * @return sysObjectId
  **/
 
  public String getSysObjectId() {
    return sysObjectId;
  }

  public void setSysObjectId(String sysObjectId) {
    this.sysObjectId = sysObjectId;
  }

  public OnmsNode labelSource(LabelSourceEnum labelSource) {
    this.labelSource = labelSource;
    return this;
  }

   /**
   * Get labelSource
   * @return labelSource
  **/
 
  public LabelSourceEnum getLabelSource() {
    return labelSource;
  }

  public void setLabelSource(LabelSourceEnum labelSource) {
    this.labelSource = labelSource;
  }

  public OnmsNode netBiosName(String netBiosName) {
    this.netBiosName = netBiosName;
    return this;
  }

   /**
   * Get netBiosName
   * @return netBiosName
  **/
 
  public String getNetBiosName() {
    return netBiosName;
  }

  public void setNetBiosName(String netBiosName) {
    this.netBiosName = netBiosName;
  }

  public OnmsNode netBiosDomain(String netBiosDomain) {
    this.netBiosDomain = netBiosDomain;
    return this;
  }

   /**
   * Get netBiosDomain
   * @return netBiosDomain
  **/
 
  public String getNetBiosDomain() {
    return netBiosDomain;
  }

  public void setNetBiosDomain(String netBiosDomain) {
    this.netBiosDomain = netBiosDomain;
  }

  public OnmsNode createTime(OffsetDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

   /**
   * Get createTime
   * @return createTime
  **/
 
  public OffsetDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(OffsetDateTime createTime) {
    this.createTime = createTime;
  }

  public OnmsNode lastEgressFlow(OffsetDateTime lastEgressFlow) {
    this.lastEgressFlow = lastEgressFlow;
    return this;
  }

   /**
   * Get lastEgressFlow
   * @return lastEgressFlow
  **/
 
  public OffsetDateTime getLastEgressFlow() {
    return lastEgressFlow;
  }

  public void setLastEgressFlow(OffsetDateTime lastEgressFlow) {
    this.lastEgressFlow = lastEgressFlow;
  }

  public OnmsNode lastCapsdPoll(OffsetDateTime lastCapsdPoll) {
    this.lastCapsdPoll = lastCapsdPoll;
    return this;
  }

   /**
   * Get lastCapsdPoll
   * @return lastCapsdPoll
  **/
 
  public OffsetDateTime getLastCapsdPoll() {
    return lastCapsdPoll;
  }

  public void setLastCapsdPoll(OffsetDateTime lastCapsdPoll) {
    this.lastCapsdPoll = lastCapsdPoll;
  }

  public OnmsNode type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
 
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OnmsNode onmsNode = (OnmsNode) o;
    return Objects.equals(this.nodeParentId, onmsNode.nodeParentId) &&
        Objects.equals(this.location, onmsNode.location) &&
        Objects.equals(this.operatingSystem, onmsNode.operatingSystem) &&
        Objects.equals(this.lastIngressFlow, onmsNode.lastIngressFlow) &&
        Objects.equals(this.nodeId, onmsNode.nodeId) &&
        Objects.equals(this.foreignSource, onmsNode.foreignSource) &&
        Objects.equals(this.label, onmsNode.label) &&
        Objects.equals(this.foreignId, onmsNode.foreignId) &&
        Objects.equals(this.assetRecord, onmsNode.assetRecord) &&
        Objects.equals(this.categories, onmsNode.categories) &&
        Objects.equals(this.sysContact, onmsNode.sysContact) &&
        Objects.equals(this.sysDescription, onmsNode.sysDescription) &&
        Objects.equals(this.sysLocation, onmsNode.sysLocation) &&
        Objects.equals(this.sysName, onmsNode.sysName) &&
        Objects.equals(this.sysObjectId, onmsNode.sysObjectId) &&
        Objects.equals(this.labelSource, onmsNode.labelSource) &&
        Objects.equals(this.netBiosName, onmsNode.netBiosName) &&
        Objects.equals(this.netBiosDomain, onmsNode.netBiosDomain) &&
        Objects.equals(this.createTime, onmsNode.createTime) &&
        Objects.equals(this.lastEgressFlow, onmsNode.lastEgressFlow) &&
        Objects.equals(this.lastCapsdPoll, onmsNode.lastCapsdPoll) &&
        Objects.equals(this.type, onmsNode.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeParentId, location, operatingSystem, lastIngressFlow, nodeId, foreignSource, label, foreignId, assetRecord, categories, sysContact, sysDescription, sysLocation, sysName, sysObjectId, labelSource, netBiosName, netBiosDomain, createTime, lastEgressFlow, lastCapsdPoll, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OnmsNode {\n");
    
    sb.append("    nodeParentId: ").append(toIndentedString(nodeParentId)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    operatingSystem: ").append(toIndentedString(operatingSystem)).append("\n");
    sb.append("    lastIngressFlow: ").append(toIndentedString(lastIngressFlow)).append("\n");
    sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
    sb.append("    foreignSource: ").append(toIndentedString(foreignSource)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    foreignId: ").append(toIndentedString(foreignId)).append("\n");
    sb.append("    assetRecord: ").append(toIndentedString(assetRecord)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    sysContact: ").append(toIndentedString(sysContact)).append("\n");
    sb.append("    sysDescription: ").append(toIndentedString(sysDescription)).append("\n");
    sb.append("    sysLocation: ").append(toIndentedString(sysLocation)).append("\n");
    sb.append("    sysName: ").append(toIndentedString(sysName)).append("\n");
    sb.append("    sysObjectId: ").append(toIndentedString(sysObjectId)).append("\n");
    sb.append("    labelSource: ").append(toIndentedString(labelSource)).append("\n");
    sb.append("    netBiosName: ").append(toIndentedString(netBiosName)).append("\n");
    sb.append("    netBiosDomain: ").append(toIndentedString(netBiosDomain)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    lastEgressFlow: ").append(toIndentedString(lastEgressFlow)).append("\n");
    sb.append("    lastCapsdPoll: ").append(toIndentedString(lastCapsdPoll)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
