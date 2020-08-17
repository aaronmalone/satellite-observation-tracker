package com.aaronmalone.satellite;

import org.springframework.data.annotation.Id;

import java.time.Instant;

public class Observation {

  @Id
  public String id;

  /**
   * Space Track name
   */
  public String name;

  /**
   * Space Track catalog number
   */
  public Integer catalogNumber;

  /**
   * COSPAR ID (see https://en.wikipedia.org/wiki/COSPAR_designation)
   */
  public String cosparId;

  /**
   * Time of observation
   */
  public Instant time;

  /**
   * Any additional notes about observation
   */
  public String notes;

  public boolean checkValidity() {
    //TODO: replace boolean method with validate() that throws exception
    /*
     * To be valid, at least one of name, catalogNumber, or cosparId
     * must be provided.
     */
    return name != null || catalogNumber != null || cosparId != null;
  }

  public void ensureThatTimeFieldIsPopulated() {
    if (this.time == null) {
      this.time = Instant.now();
    }
  }
}
