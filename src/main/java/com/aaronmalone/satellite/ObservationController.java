package com.aaronmalone.satellite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObservationController {

  @PostMapping(path = "/record")
  public ResponseEntity<?> recordObservation(@RequestBody RecordSatelliteObservation observation) {
    if (observation.isValid()) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } else {
      return ResponseEntity.unprocessableEntity().body("Name, catalog number, or COSPAR ID must be provided");
    }
  }
}
