package it.ianus.plugin.clients.opennms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Expression
 */
public class Expression {
  @JsonProperty("label")
  private String label = null;

  @JsonProperty("transient")
  private Boolean _transient = null;

  public Expression label(String label) {
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

  public Expression _transient(Boolean _transient) {
    this._transient = _transient;
    return this;
  }

   /**
   * Get _transient
   * @return _transient
  **/
  public Boolean isTransient() {
    return _transient;
  }

  public void setTransient(Boolean _transient) {
    this._transient = _transient;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Expression expression = (Expression) o;
    return Objects.equals(this.label, expression.label) &&
        Objects.equals(this._transient, expression._transient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, _transient);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Expression {\n");
    
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    _transient: ").append(toIndentedString(_transient)).append("\n");
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
