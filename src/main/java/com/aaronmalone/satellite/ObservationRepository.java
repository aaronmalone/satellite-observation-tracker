package com.aaronmalone.satellite;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObservationRepository extends MongoRepository<Observation, String> {

}
