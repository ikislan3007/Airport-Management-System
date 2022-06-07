package com.example.demo.domain.service;

import com.example.demo.domain.models.flight.FlightCreateDTO;
import com.example.demo.domain.models.flight.FlightResponseDTO;
import com.example.demo.domain.models.flight.FlightUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {
    FlightResponseDTO save(FlightCreateDTO flightCreateDTO);
    FlightResponseDTO get(Long id);
    Page<FlightResponseDTO> getAll(Pageable pageable);
    FlightResponseDTO update(FlightUpdateDTO flightUpdateDTO, Long id);
    Long delete(Long id);
    FlightResponseDTO assignAircraftToFlight(Long aircraftId, Long flightId);

}
