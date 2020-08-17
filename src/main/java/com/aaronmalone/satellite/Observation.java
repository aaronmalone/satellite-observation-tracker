package com.aaronmalone.satellite;

import java.time.Instant;

public class Observation {
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
  private Instant time;

  /**
   * Any additional notes about observation
   */
  private String notes;

  public boolean checkValidity() {
    //TODO: replace boolean method with validate() that throws exception
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

  public Instant getTime() {
    return time;
  }

  public void setTime(Instant time) {
    this.time = time;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  //todo remove getters and setters with lombok

  public void ensureThatTimeFieldIsPopulated() {
    if (this.time == null) {
      this.time = Instant.now();
    }
  }
}
