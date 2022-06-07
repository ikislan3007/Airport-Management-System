package com.example.demo.domain.models.flight;

import com.example.demo.domain.models.aircarft.AircraftResponseDTO;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

public record FlightResponseDTO(Long id,
                                LocalDateTime arrTime,
                                LocalDateTime depTime,
                                String deptAirportCode,
                                String destAirportCode,
                                @JsonIgnoreProperties({"iban", "insuranceСompany"})
                                AirlineResponseDTO airline,
                                AircraftResponseDTO aircraft)  {
}
