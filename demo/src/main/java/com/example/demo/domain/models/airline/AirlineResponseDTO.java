package com.example.demo.domain.models.airline;


import com.example.demo.domain.models.aircarft.AircraftCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record AirlineResponseDTO(Long id,
                                 String name,
                                 String webSite,
                                 String iban,
                                 String insuranceСompany,
                                 @JsonIgnoreProperties("airlineId")
                                 List<AircraftCreateDTO> aircrafts) {
}
