package com.example.demo.domain.service;

import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.Passenger;
import com.example.demo.domain.mapper.PassengerMapper;
import com.example.demo.domain.models.passenger.PassengerCreateDTO;
import com.example.demo.domain.models.passenger.PassengerResponseDTO;
import com.example.demo.domain.models.passenger.PassengerUpdateDTO;
import com.example.demo.domain.repository.FlightRepository;
import com.example.demo.domain.repository.PassengerRepository;
import com.example.demo.infrastructure.exceptions.FlightNotFoundException;
import com.example.demo.infrastructure.exceptions.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PassengerServiceImpl implements PassengerService {
    private PassengerRepository passengerRepository;
    private PassengerMapper passengerMapper;
    private FlightRepository flightRepository;


    @Override
    public PassengerResponseDTO save(PassengerCreateDTO passengerCreateDTO) {
        Passenger passengerForSave = passengerMapper.map(passengerCreateDTO);
        Passenger passengerSaved = passengerRepository.save(passengerForSave);
        return passengerMapper.map(passengerSaved);

    }

    @Override
    public PassengerResponseDTO get(Long id) {
        return passengerMapper.map(
                passengerRepository.findById(id)
                        .orElseThrow(() -> new PassengerNotFoundException(id)));
    }

    @Override
    public Page<PassengerResponseDTO> getAll(Pageable pageable) {
        return passengerRepository
                .findAll(pageable)
                .map(passengerMapper::map);
    }

    @Override
    public PassengerResponseDTO update(PassengerUpdateDTO passengerUpdateDTO, Long id) {
        Passenger passengerDb = passengerRepository.findById(id).orElseThrow(() -> new PassengerNotFoundException(id));
        Passenger passengerForUpdate = passengerMapper.map(passengerUpdateDTO);
        passengerForUpdate.setId(passengerDb.getId());
        passengerForUpdate.setFlights(passengerDb.getFlights());
        return passengerMapper.map(passengerRepository.save(passengerForUpdate));
    }

    @Override
    public Long delete(Long id) {
        if (passengerRepository.existsById(id)) {
            passengerRepository.deleteById(id);
        } else {
            throw new PassengerNotFoundException(id);
        }
        return id;
    }

    @Override
    public PassengerResponseDTO addFlightToPassenger(Long passengerId, Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException(flightId));
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new PassengerNotFoundException(passengerId));
        Set<Flight> flights = new HashSet<>();
        passenger.getFlights().forEach(flight1 -> flights.add(flight1));
        flights.add(flight);
        passenger.setFlights(flights);
        return passengerMapper.map(passengerRepository.save(passenger));
    }

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Autowired
    public void setPassengerRepository(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Autowired
    public void setPassengerMapper(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }
}
