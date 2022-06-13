package com.example.demo.domain.models.aircarft;

import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.crew.CrewResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public record AircraftResponseDTO(Long id,
                                  String registrationNumber,
                                  String model,
                                  int capacity,
                                  LocalDateTime releaseDate,
                                  LocalDateTime lastServiceDate,
                                  CrewResponseDTO crew,
                                  @JsonIgnoreProperties("aircrafts")
                                  AirlineResponseDTO airline) {
}
