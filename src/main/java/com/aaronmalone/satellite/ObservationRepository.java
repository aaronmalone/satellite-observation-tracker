package com.aaronmalone.satellite;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ObservationRepository extends MongoRepository<Observation, String> {

  List<Observation> findByCosparId(String cosparId);

  List<Observation> findByName(String name);
}
