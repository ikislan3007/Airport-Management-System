package com.example.demo.domain.service;

import com.example.demo.domain.models.airport.AirportCreateDTO;
import com.example.demo.domain.models.airport.AirportResponseDTO;
import com.example.demo.domain.models.airport.AirportUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AirportService {
    AirportResponseDTO save(AirportCreateDTO airportCreateDTO);
    AirportResponseDTO get(Long id);
    Page<AirportResponseDTO> getAll(Pageable pageable);
    AirportResponseDTO update(AirportUpdateDTO airportUpdateDTO, Long id);
    Long delete(Long id);
    AirportResponseDTO assignAirlineToAirport(Long airlineId, Long airportId);
}
