package com.example.demo.domain.models.crew;

import com.example.demo.domain.models.crewmember.CrewMemberResponseDTO;

import java.util.List;

public record CrewResponseDTO(Long id,
                              String uniqueCrewIdentifierNumber,
                              List<CrewMemberResponseDTO> crewMembers)  {
}
