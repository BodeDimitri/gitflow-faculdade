package com.example.faculdade.controller;

import com.example.faculdade.models.TrafficIncident;
import com.example.faculdade.repository.TrafficIncidentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class TrafficIncidentSteps {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrafficIncidentRepository repository;

    private TrafficIncident incident;
    private MvcResult result;

    @Autowired
    private ObjectMapper objectMapper;

    @Given("que um incidente válido está disponível")
    public void incidenteValido() {
        incident = new TrafficIncident();
        incident.setIncidentType("Acidente");
        incident.setSeverity(2);
        incident.setDescription("Batida leve");
        incident.setLocation("Av. Brasil");
    }

    @Given("um incidente inválido \\(sem location) está disponível")
    public void incidenteInvalido() {
        incident = new TrafficIncident();
        incident.setIncidentType("Acidente");
        incident.setSeverity(2);
        incident.setDescription("Batida leve");
        incident.setLocation(""); // inválido
    }

    @When("o cliente envia uma requisição POST para criar o incidente")
    public void enviarRequisicaoPOST() throws Exception {
        Mockito.when(repository.save(Mockito.any())).thenReturn(incident);

        result = mockMvc.perform(post("/traffic-incidents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incident)))
                .andReturn();
    }

    @When("o cliente envia uma requisição GET para o endpoint \\/traffic-incidents\\/1")
    public void enviarGET() throws Exception {
        incident.setId(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(incident));

        result = mockMvc.perform(get("/traffic-incidents/1")).andReturn();
    }

    @Then("o sistema deve retornar o status {int}")
    public void validarStatus(int status) throws Exception {
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().is(status));
    }

    @Then("o corpo da resposta deve conter o campo {string} com valor {string}")
    public void validarCampo(String campo, String valor) throws Exception {
        mockMvc.perform(asyncDispatch(result))
                .andExpect(jsonPath("$." + campo).value(valor));
    }
}
