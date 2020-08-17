package com.aaronmalone.satellite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ObservationControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testObservationsRouteReturnsListOfObservations() {
    String url = "http://localhost:" + port + "/api/observation";
    ParameterizedTypeReference<List<Observation>> responseType =
            new ParameterizedTypeReference<List<Observation>>() {
            };

    ResponseEntity<List<Observation>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    List<Observation> observations = response.getBody();
    assertNotNull(observations);
  }
}
