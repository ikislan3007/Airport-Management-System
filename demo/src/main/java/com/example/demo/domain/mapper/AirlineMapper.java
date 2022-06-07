package com.example.demo.domain.mapper;

import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.models.airline.AirlineCreateDTO;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.airline.AirlineUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    Airline map(AirlineCreateDTO airlineCreateDTO);
    Airline map(AirlineUpdateDTO airlineUpdateDTO);
    AirlineResponseDTO map(Airline airline);
}
