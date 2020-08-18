package com.aaronmalone.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SearchController {

  private static final String BAD_SEARCH_MSG = "Specify either COSPAR ID or name.";

  @Autowired
  private ObservationRepository observationRepository;

  @GetMapping(path = "/api/search-observations")
  public List<Observation> searchObservations(@RequestParam(required = false) String cosparId, @RequestParam(required = false) String name) {
    // for now, we'll only allow searching based on one parameter
    if (cosparId != null && name != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_SEARCH_MSG);
    }
    if (cosparId != null) {
      return observationRepository.findByCosparId(cosparId);
    } else if (name != null) {
      return observationRepository.findByName(name);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_SEARCH_MSG);
    }
  }
}
