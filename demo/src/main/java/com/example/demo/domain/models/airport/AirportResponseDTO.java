package com.example.demo.domain.models.airport;

import com.example.demo.domain.models.airline.AirlineResponseDTO;
import java.util.List;


public record AirportResponseDTO(Long id,
                                 String name,
                                 String code,
                                 String address,
                                 String phoneNumber,
                                 List<AirlineResponseDTO> airlines,
                                 boolean disabled) {
}
