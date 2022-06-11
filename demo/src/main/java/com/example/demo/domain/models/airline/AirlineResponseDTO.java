package com.example.demo.domain.models.airline;


import com.example.demo.domain.models.aircarft.AircraftCreateDTO;

import java.util.List;

public record AirlineResponseDTO(Long id,
                                 String name,
                                 String webSite,
                                 String iban,
                                 String insuranceСompany,
                                 List<AircraftCreateDTO> aircrafts) {
}
