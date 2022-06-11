package com.example.demo.domain.models.flight;

import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.airport.AirportResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public record FlightResponseDTO(Long id,
                                String flightNumber,
                                LocalDateTime arrTime,
                                LocalDateTime depTime,
                                @JsonIgnoreProperties({"airlines"})
                                AirportResponseDTO deptAirport,
                                @JsonIgnoreProperties({"airlines"})
                                AirportResponseDTO destAirport,
                                @JsonIgnoreProperties({"iban", "insuranceСompany", "aircrafts"})
                                AirlineResponseDTO airline) {
}
