package com.example.demo.domain.mapper;

import com.example.demo.domain.entity.Crew;
import com.example.demo.domain.entity.CrewMember;
import com.example.demo.domain.models.crew.CrewCreateDTO;
import com.example.demo.domain.models.crew.CrewResponseDTO;
import com.example.demo.domain.models.crew.CrewUpdateDTO;
import com.example.demo.domain.models.crewmember.CrewMemberResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrewMapper {
    Crew map(CrewCreateDTO crewCreateDTO);
    Crew map(CrewUpdateDTO crewUpdateDTO);
    CrewResponseDTO map(Crew crew);
    CrewMemberResponseDTO map(CrewMember crewMember);
}
