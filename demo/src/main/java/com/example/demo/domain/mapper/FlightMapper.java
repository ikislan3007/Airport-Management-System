package com.example.demo.domain.mapper;

import com.example.demo.domain.entity.Aircraft;
import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.models.aircarft.AircraftResponseDTO;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.airport.AirportResponseDTO;
import com.example.demo.domain.models.flight.FlightCreateDTO;
import com.example.demo.domain.models.flight.FlightResponseDTO;
import com.example.demo.domain.models.flight.FlightUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    Flight map(FlightCreateDTO flightCreateDTO);
    Flight map(FlightUpdateDTO flightUpdateDTO);
    FlightResponseDTO map(Flight flight);
    AirlineResponseDTO map(Airline airline);
    AircraftResponseDTO map(Aircraft aircraft);
    AirportResponseDTO map(Airport airport);
}

