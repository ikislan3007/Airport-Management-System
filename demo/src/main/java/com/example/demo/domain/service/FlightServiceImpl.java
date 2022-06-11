package com.example.demo.domain.service;

import com.example.demo.domain.entity.Aircraft;
import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.Passenger;
import com.example.demo.domain.mapper.FlightMapper;
import com.example.demo.domain.models.flight.FlightCreateDTO;
import com.example.demo.domain.models.flight.FlightResponseDTO;
import com.example.demo.domain.models.flight.FlightUpdateDTO;
import com.example.demo.domain.repository.AircraftRepository;
import com.example.demo.domain.repository.AirlineRepository;
import com.example.demo.domain.repository.FlightRepository;
import com.example.demo.domain.repository.PassengerRepository;
import com.example.demo.infrastructure.AircraftNotFoundException;
import com.example.demo.infrastructure.AirlineNotFoundException;
import com.example.demo.infrastructure.FlightNotFoundException;
import com.example.demo.infrastructure.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private FlightMapper flightMapper;
    private AirlineRepository airlineRepository;
    private AircraftRepository aircraftRepository;
    private PassengerRepository passengerRepository;


    @Override
    public FlightResponseDTO save(FlightCreateDTO flightCreateDTO) {
        Airline airline = airlineRepository.findById(flightCreateDTO.airlineId()).orElseThrow(() -> new AirlineNotFoundException(flightCreateDTO.airlineId()));
        Flight flightForSave = flightMapper.map(flightCreateDTO);
        flightForSave.setAirline(airline);
        Flight flightSaved = flightRepository.save(flightForSave);
        return flightMapper.map(flightSaved);
    }

    @Override
    public FlightResponseDTO get(Long id) {
        return flightMapper.map(
                flightRepository.findById(id)
                        .orElseThrow(() -> new FlightNotFoundException(id)));
    }

    @Override
    public Page<FlightResponseDTO> getAll(Pageable pageable) {
        return flightRepository
                .findAll(pageable)
                .map(flightMapper::map);
    }

    @Override
    public FlightResponseDTO update(FlightUpdateDTO flightUpdateDTO, Long id) {
        Flight flightDb = flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException(id));
        Flight flightForUpdate = flightMapper.map(flightUpdateDTO);
        flightForUpdate.setId(flightDb.getId());
        flightForUpdate.setAirline(flightDb.getAirline());
        return flightMapper.map(flightRepository.save(flightForUpdate));
    }

    @Override
    public Long delete(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException(id));

        Set<Passenger> passengersForDeletion = flight.getPassengers();
        passengersForDeletion.forEach(passenger -> passenger.getFlights().remove(flight));


        flightRepository.deleteById(id);

        return id;
    }

    @Override
    public FlightResponseDTO assignAircraftToFlight(Long aircraftId, Long flightId) {
        Aircraft aircraft = aircraftRepository.findById(aircraftId).orElseThrow(() -> new AircraftNotFoundException(aircraftId));
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException(flightId));
        flight.setAircraft(aircraft);
        return flightMapper.map(flightRepository.save(flight));
    }


    @Autowired
    public void setPassengerRepository(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Autowired
    public void setFlightMapper(FlightMapper flightMapper) {
        this.flightMapper = flightMapper;
    }

    @Autowired
    public void setAirlineRepository(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Autowired
    public void setAircraftRepository(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }
}
