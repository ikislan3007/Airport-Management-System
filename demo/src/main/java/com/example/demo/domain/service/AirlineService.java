package com.example.demo.domain.service;

import com.example.demo.domain.models.airline.AirlineCreateDTO;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.airline.AirlineUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AirlineService {
    AirlineResponseDTO save(AirlineCreateDTO airlineCreateDTO);
    AirlineResponseDTO get(Long id);
    Page<AirlineResponseDTO> getAll(Pageable pageable);
    AirlineResponseDTO update(AirlineUpdateDTO airlineUpdateDTO, Long id);
    Long delete(Long id);
    AirlineResponseDTO assignAircraftToAirline(Long airlineId, Long  aircraftId);
}

