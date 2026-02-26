package org.opennms.integrations.ianus.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * RrdGraphAttribute
 */
public class RrdGraphAttribute {

  @JsonProperty("name")
  private String name;

  @JsonProperty("relativePath")
  private String relativePath;

  @JsonProperty("rrdFile")
  private String rrdFile;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRelativePath() {
    return relativePath;
  }

  public void setRelativePath(String relativePath) {
    this.relativePath = relativePath;
  }

  public String getRrdFile() {
    return rrdFile;
  }

  public void setRrdFile(String rrdFile) {
    this.rrdFile = rrdFile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RrdGraphAttribute that = (RrdGraphAttribute) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(relativePath, that.relativePath) &&
        Objects.equals(rrdFile, that.rrdFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, relativePath, rrdFile);
  }

  @Override
  public String toString() {
    return "RrdGraphAttribute{" +
        "name='" + name + '\'' +
        ", relativePath='" + relativePath + '\'' +
        ", rrdFile='" + rrdFile + '\'' +
        '}';
  }
}
