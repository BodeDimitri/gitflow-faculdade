package com.example.faculdade.controller;

import com.example.faculdade.models.TrafficIncident;
import com.example.faculdade.repository.TrafficIncidentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traffic-incidents")
@Validated
public class TrafficIncidentController {

    @Autowired
    private TrafficIncidentRepository repository;

    @GetMapping
    public List<TrafficIncident> getAllIncidents() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<TrafficIncident> createIncident(@Valid @RequestBody TrafficIncident incident) {
        return new ResponseEntity<>(repository.save(incident), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrafficIncident> getIncidentById(@PathVariable Long id) {
        Optional<TrafficIncident> incident = repository.findById(id);
        if (incident.isPresent()) {
            return new ResponseEntity<>(incident.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrafficIncident> updateIncident(@PathVariable Long id, @Valid @RequestBody TrafficIncident incidentDetails) {
        return repository.findById(id).map(incident -> {
            incident.setIncidentType(incidentDetails.getIncidentType());
            incident.setSeverity(incidentDetails.getSeverity());
            incident.setDescription(incidentDetails.getDescription());
            incident.setLocation(incidentDetails.getLocation());
            return new ResponseEntity<>(repository.save(incident), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        return repository.findById(id).map(incident -> {
            repository.delete(incident);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
