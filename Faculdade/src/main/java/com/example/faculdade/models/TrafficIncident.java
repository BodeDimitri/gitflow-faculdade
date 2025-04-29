package com.example.faculdade.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class TrafficIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O tipo do incidente é obrigatório.")
    @Size(max = 50, message = "O tipo do incidente não pode exceder 50 caracteres.")
    private String incidentType;

    @NotNull(message = "A severidade do incidente é obrigatória.")
    private Integer severity;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(max = 255, message = "A descrição não pode exceder 255 caracteres.")
    private String description;

    @NotNull(message = "A localização do incidente é obrigatória.")
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
