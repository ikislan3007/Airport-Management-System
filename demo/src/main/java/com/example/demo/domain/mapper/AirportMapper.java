package com.example.demo.domain.mapper;

import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.airport.AirportCreateDTO;
import com.example.demo.domain.models.airport.AirportResponseDTO;
import com.example.demo.domain.models.airport.AirportUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    Airport map(AirportCreateDTO airportCreateDTO);
    Airport map(AirportUpdateDTO airportUpdateDTO);
    AirportResponseDTO map(Airport airport);
    AirlineResponseDTO map(Airline airline);
}