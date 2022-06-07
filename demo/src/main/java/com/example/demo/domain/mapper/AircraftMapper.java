package com.example.demo.domain.mapper;

import com.example.demo.domain.entity.Aircraft;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.models.aircarft.AircraftCreateDTO;
import com.example.demo.domain.models.aircarft.AircraftResponseDTO;
import com.example.demo.domain.models.aircarft.AircraftUpdateDTO;
import com.example.demo.domain.models.flight.FlightResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AircraftMapper {
    Aircraft map(AircraftCreateDTO aircraftCreateDTO);
    Aircraft map(AircraftUpdateDTO aircraftUpdateDTO);
    AircraftResponseDTO map(Aircraft aircraft);
    FlightResponseDTO map(Flight flight);
}
