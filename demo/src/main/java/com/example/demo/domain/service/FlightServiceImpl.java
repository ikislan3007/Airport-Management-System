package com.example.demo.domain.service;

import com.example.demo.domain.entity.*;
import com.example.demo.domain.mapper.FlightMapper;
import com.example.demo.domain.models.flight.FlightCreateDTO;
import com.example.demo.domain.models.flight.FlightResponseDTO;
import com.example.demo.domain.models.flight.FlightUpdateDTO;
import com.example.demo.domain.repository.AircraftRepository;
import com.example.demo.domain.repository.AirlineRepository;
import com.example.demo.domain.repository.AirportRepository;
import com.example.demo.domain.repository.FlightRepository;
import com.example.demo.infrastructure.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private FlightMapper flightMapper;
    private AirlineRepository airlineRepository;
    private AircraftRepository aircraftRepository;
    private AirportRepository airportRepository;


    @Override
    public FlightResponseDTO save(FlightCreateDTO flightCreateDTO) {
        Airline airline = airlineRepository.findById(flightCreateDTO.airlineId()).orElseThrow(() -> new AirlineNotFoundException(flightCreateDTO.airlineId()));
        Airport deptAirport = airportRepository.findById(flightCreateDTO.deptAirportId()).orElseThrow(() -> new AirportNotFoundException(flightCreateDTO.deptAirportId()));
        Airport destAirport = airportRepository.findById(flightCreateDTO.destAirportId()).orElseThrow(() -> new AirportNotFoundException(flightCreateDTO.destAirportId()));


        Flight flightForSave = flightMapper.map(flightCreateDTO);
        checkIfAirportServeAirline(deptAirport.getId(),flightCreateDTO.airlineId());
        flightForSave.setDeptAirport(deptAirport);

        checkIfAirportServeAirline(destAirport.getId(),flightCreateDTO.airlineId());
        flightForSave.setDestAirport(destAirport);


        flightForSave.setAirline(airline);

        flightForSave.setDestAirport(destAirport);
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

        Airport deptAirport = airportRepository.findById(flightUpdateDTO.deptAirportId()).orElseThrow(() -> new AirportNotFoundException(flightUpdateDTO.deptAirportId()));
        Airport destAirport = airportRepository.findById(flightUpdateDTO.destAirportId()).orElseThrow(() -> new AirportNotFoundException(flightUpdateDTO.destAirportId()));
        Flight flightForUpdate = flightMapper.map(flightUpdateDTO);



        checkIfAirportServeAirline(deptAirport.getId(),flightUpdateDTO.airlineId());
        flightForUpdate.setDeptAirport(deptAirport);

        checkIfAirportServeAirline(destAirport.getId(),flightUpdateDTO.airlineId());
        flightForUpdate.setDestAirport(destAirport);

        flightForUpdate.setId(flightDb.getId());
        flightForUpdate.setAirline(flightDb.getAirline());
        flightForUpdate.setDeptAirport(deptAirport);
        flightForUpdate.setDestAirport(destAirport);
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

    public void checkIfAirportServeAirline(Long airportId, Long airlineId){
        Airport airport=airportRepository.findById(airportId).orElseThrow(() -> new AirportNotFoundException(airportId));
        Airline airline=airlineRepository.findById(airlineId).orElseThrow(() -> new AirlineNotFoundException(airlineId));
        if(!(airport.getAirlines().contains(airline) && !(airport.isDisabled()))){
            throw new AirportDoesNotServeAirlineException(airport.getId(),airline.getId());
        }

    }


    @Override
    public FlightResponseDTO assignAircraftToFlight(Long aircraftId, Long flightId) {
        Aircraft aircraft = aircraftRepository.findById(aircraftId).orElseThrow(() -> new AircraftNotFoundException(aircraftId));
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException(flightId));
         flight.setAircraft(aircraft);
        return flightMapper.map(flightRepository.save(flight));
    }


    @Autowired
    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
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
