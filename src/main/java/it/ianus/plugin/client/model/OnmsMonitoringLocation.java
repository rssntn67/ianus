package it.ianus.plugin.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OnmsMonitoringLocation
 */

public class OnmsMonitoringLocation {
  @JsonProperty("locationName")
  private String locationName = null;

  @JsonProperty("geolocation")
  private String geolocation = null;

  @JsonProperty("longitude")
  private Float longitude = null;

  @JsonProperty("latitude")
  private Float latitude = null;

  @JsonProperty("monitoringArea")
  private String monitoringArea = null;

  @JsonProperty("tags")
  private List<String> tags = null;

  @JsonProperty("priority")
  private Long priority = null;

  public OnmsMonitoringLocation locationName(String locationName) {
    this.locationName = locationName;
    return this;
  }

   /**
   * Get locationName
   * @return locationName
  **/
  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public OnmsMonitoringLocation geolocation(String geolocation) {
    this.geolocation = geolocation;
    return this;
  }

   /**
   * Get geolocation
   * @return geolocation
  **/
  public String getGeolocation() {
    return geolocation;
  }

  public void setGeolocation(String geolocation) {
    this.geolocation = geolocation;
  }

  public OnmsMonitoringLocation longitude(Float longitude) {
    this.longitude = longitude;
    return this;
  }

   /**
   * Get longitude
   * @return longitude
  **/
  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }

  public OnmsMonitoringLocation latitude(Float latitude) {
    this.latitude = latitude;
    return this;
  }

   /**
   * Get latitude
   * @return latitude
  **/
  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public OnmsMonitoringLocation monitoringArea(String monitoringArea) {
    this.monitoringArea = monitoringArea;
    return this;
  }

   /**
   * Get monitoringArea
   * @return monitoringArea
  **/
  public String getMonitoringArea() {
    return monitoringArea;
  }

  public void setMonitoringArea(String monitoringArea) {
    this.monitoringArea = monitoringArea;
  }

  public OnmsMonitoringLocation tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public OnmsMonitoringLocation addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public OnmsMonitoringLocation priority(Long priority) {
    this.priority = priority;
    return this;
  }

   /**
   * Get priority
   * @return priority
  **/
  public Long getPriority() {
    return priority;
  }

  public void setPriority(Long priority) {
    this.priority = priority;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OnmsMonitoringLocation onmsMonitoringLocation = (OnmsMonitoringLocation) o;
    return Objects.equals(this.locationName, onmsMonitoringLocation.locationName) &&
        Objects.equals(this.geolocation, onmsMonitoringLocation.geolocation) &&
        Objects.equals(this.longitude, onmsMonitoringLocation.longitude) &&
        Objects.equals(this.latitude, onmsMonitoringLocation.latitude) &&
        Objects.equals(this.monitoringArea, onmsMonitoringLocation.monitoringArea) &&
        Objects.equals(this.tags, onmsMonitoringLocation.tags) &&
        Objects.equals(this.priority, onmsMonitoringLocation.priority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locationName, geolocation, longitude, latitude, monitoringArea, tags, priority);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OnmsMonitoringLocation {\n");
    
    sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
    sb.append("    geolocation: ").append(toIndentedString(geolocation)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    monitoringArea: ").append(toIndentedString(monitoringArea)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
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
