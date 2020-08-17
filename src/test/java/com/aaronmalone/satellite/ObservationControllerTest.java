package com.aaronmalone.satellite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ObservationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testObservationsRouteReturnsListOfObservations() throws Exception {

    MvcResult mvcResult = mockMvc
            .perform(get("/api/observation"))
            .andExpect(status().isOk())
            .andReturn();

    String json = mvcResult.getResponse().getContentAsString();
    TypeReference<List<Observation>> listOfObservation = new TypeReference<List<Observation>>() {
    };
    List<Observation> observations = objectMapper.readValue(json, listOfObservation);
    assertFalse(observations.isEmpty());
  }
}
