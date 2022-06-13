package com.example.demo.domain.mapper;

import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.entity.CrewMember;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.crewmember.CrewMemberCreateDTO;
import com.example.demo.domain.models.crewmember.CrewMemberResponseDTO;
import com.example.demo.domain.models.crewmember.CrewMemberUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrewMemberMapper {
    CrewMember map(CrewMemberCreateDTO crewMemberCreateDTO);
    CrewMember map(CrewMemberUpdateDTO crewMemberUpdateDTO);
    CrewMemberResponseDTO map(CrewMember crewMember);
    AirlineResponseDTO map(Airline airline);
}
