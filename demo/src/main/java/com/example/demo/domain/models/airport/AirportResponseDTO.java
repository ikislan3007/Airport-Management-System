package com.example.demo.domain.models.airport;

import com.example.demo.domain.entity.Airline;

import java.util.Set;


public record AirportResponseDTO(Long id,
                                 String name,
                                 String code,
                                 String address,
                                 String phoneNumber,
                                 Set<Airline> airlines,
                                 boolean disabled) {
}
