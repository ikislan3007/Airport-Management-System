package com.example.demo.domain.service;

import com.example.demo.domain.models.crewmember.CrewMemberCreateDTO;
import com.example.demo.domain.models.crewmember.CrewMemberResponseDTO;
import com.example.demo.domain.models.crewmember.CrewMemberUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrewMemberService {
    CrewMemberResponseDTO save(CrewMemberCreateDTO crewMemberCreateDTO);
    CrewMemberResponseDTO get(Long id);
    Page<CrewMemberResponseDTO> getAll(Pageable pageable);
    CrewMemberResponseDTO update(CrewMemberUpdateDTO crewMemberUpdateDTO, Long id);
    Long delete(Long id);
}
