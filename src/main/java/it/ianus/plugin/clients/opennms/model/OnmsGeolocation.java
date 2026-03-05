package it.ianus.plugin.clients.opennms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * OnmsGeolocation
 */
public class OnmsGeolocation {
  @JsonProperty("address1")
  private String address1 = null;

  @JsonProperty("address2")
  private String address2 = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("zip")
  private String zip = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("longitude")
  private Double longitude = null;

  @JsonProperty("latitude")
  private Double latitude = null;

  @JsonProperty("state")
  private String state = null;

  public OnmsGeolocation address1(String address1) {
    this.address1 = address1;
    return this;
  }

   /**
   * Get address1
   * @return address1
  **/
  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public OnmsGeolocation address2(String address2) {
    this.address2 = address2;
    return this;
  }

   /**
   * Get address2
   * @return address2
  **/
  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public OnmsGeolocation city(String city) {
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public OnmsGeolocation zip(String zip) {
    this.zip = zip;
    return this;
  }

   /**
   * Get zip
   * @return zip
  **/
  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public OnmsGeolocation country(String country) {
    this.country = country;
    return this;
  }

   /**
   * Get country
   * @return country
  **/
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public OnmsGeolocation longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

   /**
   * Get longitude
   * @return longitude
  **/
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public OnmsGeolocation latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

   /**
   * Get latitude
   * @return latitude
  **/
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public OnmsGeolocation state(String state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OnmsGeolocation onmsGeolocation = (OnmsGeolocation) o;
    return Objects.equals(this.address1, onmsGeolocation.address1) &&
        Objects.equals(this.address2, onmsGeolocation.address2) &&
        Objects.equals(this.city, onmsGeolocation.city) &&
        Objects.equals(this.zip, onmsGeolocation.zip) &&
        Objects.equals(this.country, onmsGeolocation.country) &&
        Objects.equals(this.longitude, onmsGeolocation.longitude) &&
        Objects.equals(this.latitude, onmsGeolocation.latitude) &&
        Objects.equals(this.state, onmsGeolocation.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address1, address2, city, zip, country, longitude, latitude, state);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OnmsGeolocation {\n");
    
    sb.append("    address1: ").append(toIndentedString(address1)).append("\n");
    sb.append("    address2: ").append(toIndentedString(address2)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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
