package com.example.demo.domain.service;

import com.example.demo.domain.models.aircarft.AircraftCreateDTO;
import com.example.demo.domain.models.aircarft.AircraftResponseDTO;
import com.example.demo.domain.models.aircarft.AircraftUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AircraftService {
    AircraftResponseDTO save(AircraftCreateDTO aircraftCreateDTO);
    AircraftResponseDTO get(Long id);
    Page< AircraftResponseDTO> getAll(Pageable pageable);
    AircraftResponseDTO update(AircraftUpdateDTO aircraftUpdateDTO, Long id);
    Long delete(Long id);
}
