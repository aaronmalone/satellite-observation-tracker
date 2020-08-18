package com.aaronmalone.satellite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

  @Test
  public void testPostObservation() throws Exception {
    Map<String, String> postContent = new HashMap<>();
    postContent.put("name", "HST");
    postContent.put("notes", "bright!");

    MvcResult mvcResult = mockMvc
            .perform(
                    post("/api/observation")
                            .content(objectMapper.writeValueAsString(postContent))
                            .contentType("application/json")
                            .header("Authorization", getAuthorizationHeaderValue())
            )
            .andExpect(status().isCreated())
            .andReturn();

    String json = mvcResult.getResponse().getContentAsString();
    Observation created = objectMapper.readValue(json, Observation.class);
    assertNotNull(created.getId());
    assertNotNull(created.getTime());
    assertEquals("HST", created.getName());
  }

  private Object getAuthorizationHeaderValue() {
    String username = "observer";
    String password = "pazzw0rd";
    String concatenated = username + ":" + password;
    String encoded = Base64.getEncoder().encodeToString(concatenated.getBytes());
    return "Basic " + encoded;
  }
}
