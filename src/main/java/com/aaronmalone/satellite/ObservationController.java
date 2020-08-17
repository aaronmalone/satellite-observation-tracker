package com.aaronmalone.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObservationController {

  @Autowired
  private ObservationRepository observationRepository;

  @PostMapping(path = "/record")
  public ResponseEntity<?> recordObservation(@RequestBody Observation observation) {
    if (observation.checkValidity()) {
      observation.ensureThatTimeFieldIsPopulated();
      Observation saved = observationRepository.save(observation);
      return ResponseEntity.created(null).body(saved);
    } else {
      return ResponseEntity.unprocessableEntity().body("Name, catalog number, or COSPAR ID must be provided");
    }
  }
}
