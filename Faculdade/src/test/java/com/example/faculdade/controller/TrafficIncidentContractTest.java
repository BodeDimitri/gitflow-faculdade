package com.example.faculdade.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.faculdade.repository.TrafficIncidentRepository;
import com.example.faculdade.models.TrafficIncident;

import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.mockito.Mockito.when;

@WebMvcTest
public class TrafficIncidentContractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrafficIncidentRepository repository;

    @Test
    void validaContrato() {
        TrafficIncident incident = new TrafficIncident();
        incident.setId(1L);
        incident.setIncidentType("Acidente");
        incident.setSeverity(2);
        incident.setDescription("Batida leve");
        incident.setLocation("Av. Brasil");

        when(repository.findById(1L)).thenReturn(Optional.of(incident));

        given()
            .mockMvc(mockMvc)
        .when()
            .get("/traffic-incidents/1")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/incident-schema.json"));
    }
}
