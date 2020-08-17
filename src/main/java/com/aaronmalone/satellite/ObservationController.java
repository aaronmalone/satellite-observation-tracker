package com.aaronmalone.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ObservationController {

  @Autowired
  private ObservationRepository observationRepository;

  @PostMapping(path = "/api/observation")
  public ResponseEntity<?> recordObservation(@RequestBody Observation observation) {
    if (observation.checkValidity()) {
      observation.ensureThatTimeFieldIsPopulated();
      Observation saved = observationRepository.save(observation);
      return ResponseEntity.created(null).body(saved);
    } else {
      return ResponseEntity.unprocessableEntity().body("Name, catalog number, or COSPAR ID must be provided");
    }
  }

  @GetMapping(path = "/api/observation")
  public ResponseEntity<List<Observation>> allObservations() {
    List<Observation> all = observationRepository.findAll();
    return ResponseEntity.ok(all);
  }
}
