package org.codechallenge.patternrecognition.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.codechallenge.patternrecognition.PatternRecognitionApp;
import org.codechallenge.patternrecognition.model.AddPointRequest;
import org.codechallenge.patternrecognition.repository.LineRepository;
import org.codechallenge.patternrecognition.repository.SpaceRepository;
import org.codechallenge.patternrecognition.service.impl.PatternRecognitionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Unit test for AppController.
 *
 *  @author Marco Pappalardo
 *  @see AppController
 *  @since 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PatternRecognitionApp.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PatternRecognitionService service;
    @Autowired
    LineRepository lineRepository;
    @Autowired
    SpaceRepository spaceRepository;

    @Test
    @Order(1)
    void addPoint_response200() throws Exception {
        AddPointRequest request = new AddPointRequest(0, 0);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/point")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.point.x", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.point.y", Matchers.is(0)));
    }

    @Test
    @Order(2)
    void addPoint_response406() throws Exception {
        AddPointRequest request = new AddPointRequest(0, 0);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/point")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                )
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_ACCEPTABLE.value()));
    }

    @Test
    @Order(3)
    void getSpace_response200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/space")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.space.points", Matchers.hasSize(1)));
    }

    @Test
    @Order(4)
    void getLinesThroughNPoints_response406() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lines/{n}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_ACCEPTABLE.value()));
    }

    @Test
    @Order(5)
    void getLinesThroughNPoints_response200_empty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lines/{n}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lines", Matchers.hasSize(0)));
    }

    @Test
    @Order(6)
    void getLinesThroughNPoints_response200_3PTS_aligned() throws Exception {
        service.addPoint(new AddPointRequest(1, 1));
        service.addPoint(new AddPointRequest(2, 2));
        service.addPoint(new AddPointRequest(-4, 1));
        service.addPoint(new AddPointRequest(2, 3));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lines/{n}", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lines", Matchers.hasSize(1)));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lines/{n}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lines", Matchers.hasSize(0)));
    }

    @Test
    @Order(7)
    void deleteSpace_response204() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/space")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertTrue(lineRepository.getAll().isEmpty());
        Assertions.assertTrue(spaceRepository.get().getPoints().isEmpty());
    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
