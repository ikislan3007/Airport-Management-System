package com.example.demo.domain.mapper;


import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.Passenger;
import com.example.demo.domain.models.flight.FlightResponseDTO;
import com.example.demo.domain.models.passenger.PassengerCreateDTO;
import com.example.demo.domain.models.passenger.PassengerResponseDTO;
import com.example.demo.domain.models.passenger.PassengerUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    Passenger map(PassengerCreateDTO passengerCreateDTO);
    Passenger map(PassengerUpdateDTO passengerUpdateDTO);
    PassengerResponseDTO map(Passenger passenger);
    FlightResponseDTO map(Flight flights);
}
