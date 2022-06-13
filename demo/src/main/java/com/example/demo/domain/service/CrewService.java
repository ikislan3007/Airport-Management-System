package com.example.demo.domain.service;

import com.example.demo.domain.models.crew.CrewCreateDTO;
import com.example.demo.domain.models.crew.CrewResponseDTO;
import com.example.demo.domain.models.crew.CrewUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrewService {
    CrewResponseDTO save(CrewCreateDTO crewCreateDTO);
    CrewResponseDTO get(Long id);
    Page<CrewResponseDTO> getAll(Pageable pageable);
    CrewResponseDTO update(CrewUpdateDTO crewUpdateDTO, Long id);
    Long delete(Long id);
}
