package com.aaronmalone.satellite;

public class RecordSatelliteObservation {
  /**
   * Space Track name
   */
  private String name;

  /**
   * Space Track catalog number
   */
  private Integer catalogNumber;

  /**
   * COSPAR ID (see https://en.wikipedia.org/wiki/COSPAR_designation)
   */
  private String cosparId;

  /**
   * Time of observation
   */
  private String timestamp;

  public boolean isValid() {
    /*
     * To be valid, at least one of name, catalogNumber, or cosparId
     * must be provided.
     */
    return name != null || catalogNumber != null || cosparId != null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(Integer catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  public String getCosparId() {
    return cosparId;
  }

  public void setCosparId(String cosparId) {
    this.cosparId = cosparId;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
