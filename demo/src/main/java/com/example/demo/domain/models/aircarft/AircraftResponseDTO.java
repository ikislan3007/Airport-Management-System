package com.example.demo.domain.models.aircarft;

import java.time.LocalDateTime;

public record AircraftResponseDTO(Long id,
                                  String registrationNumber,
                                  String model,
                                  int capacity,
                                  LocalDateTime releaseDate,
                                  LocalDateTime lastServiceDate

) {
}
