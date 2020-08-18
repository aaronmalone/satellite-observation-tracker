package com.aaronmalone.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class ObservationController {

  @Autowired
  private ObservationRepository observationRepository;

  @PostMapping(path = "/api/observation")
  public ResponseEntity<?> recordObservation(@RequestBody Observation observation) {
    if (validObservation(observation)) {
      setTimeIfUnspecified(observation);
      Observation saved = observationRepository.save(observation);
      return ResponseEntity.created(null).body(saved);
    } else {
      return ResponseEntity.unprocessableEntity().body("Name or COSPAR ID must be provided");
    }
  }

  private boolean validObservation(Observation observation) {
    // either name or COSPAR ID must be specified
    return observation.getName() != null || observation.getCosparId() != null;
  }

  private void setTimeIfUnspecified(Observation observation) {
    if (observation.getTime() == null) {
      observation.setTime(Instant.now());
    }
  }

  @GetMapping(path = "/api/observation")
  public ResponseEntity<List<Observation>> allObservations() {
    List<Observation> all = observationRepository.findAll();
    return ResponseEntity.ok(all);
  }
}
