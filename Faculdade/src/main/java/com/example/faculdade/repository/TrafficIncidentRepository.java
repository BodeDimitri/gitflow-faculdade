package com.example.faculdade.repository;

import com.example.faculdade.models.TrafficIncident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficIncidentRepository extends JpaRepository<TrafficIncident, Long> {
}
