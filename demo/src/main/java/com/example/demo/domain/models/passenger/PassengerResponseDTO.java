package com.example.demo.domain.models.passenger;

import com.example.demo.domain.models.flight.FlightResponseDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public record PassengerResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Date birthDate,
        FlightResponseDTO flight) {
}
