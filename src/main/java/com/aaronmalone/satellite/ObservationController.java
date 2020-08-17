package com.aaronmalone.satellite;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObservationController {

  @PostMapping(path = "/record")
  public ResponseEntity<?> recordObservation(@RequestBody Observation observation) {
    if (observation.checkValidity()) {
      observation.ensureThatTimeFieldIsPopulated();
      return ResponseEntity.created(null).body(observation);
    } else {
      return ResponseEntity.unprocessableEntity().body("Name, catalog number, or COSPAR ID must be provided");
    }
  }
}
