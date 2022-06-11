package com.example.demo.domain.service;

import com.example.demo.domain.entity.Aircraft;
import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.mapper.AircraftMapper;
import com.example.demo.domain.mapper.AirlineMapper;
import com.example.demo.domain.models.aircarft.AircraftCreateDTO;
import com.example.demo.domain.models.airline.AirlineCreateDTO;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.airline.AirlineUpdateDTO;
import com.example.demo.domain.repository.AircraftRepository;
import com.example.demo.domain.repository.AirlineRepository;
import com.example.demo.domain.repository.FlightRepository;
import com.example.demo.infrastructure.AircraftNotFoundException;
import com.example.demo.infrastructure.AirlineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AirlineServiceImpl implements AirlineService {
    private AirlineRepository airlineRepository;
    private FlightRepository flightRepository;
    private AirlineMapper airlineMapper;
    private AircraftMapper aircraftMapper;
    private AircraftRepository aircraftRepository;

    @Override
    public AirlineResponseDTO save(AirlineCreateDTO airlineCreateDTO) {
        Airline airlineForSave = airlineMapper.map(airlineCreateDTO);
        List<Aircraft> createdAirCrafts = new ArrayList<>();
        for (AircraftCreateDTO aircraftForCreation : airlineCreateDTO.aircrafts()) {
            createdAirCrafts.add(aircraftRepository.save(aircraftMapper.map(aircraftForCreation)));
        }
        airlineForSave.setAircrafts(createdAirCrafts);
        Airline airlineSaved = airlineRepository.save(airlineForSave);
        return airlineMapper.map(airlineSaved);
    }

    @Override
    public AirlineResponseDTO get(Long id) {
        return
                airlineMapper.map(
                        airlineRepository.findById(id)
                                .orElseThrow(() -> new AirlineNotFoundException(id)));
    }

    @Override
    public Page<AirlineResponseDTO> getAll(Pageable pageable) {
        return airlineRepository
                .findAll(pageable)
                .map(airlineMapper::map);
    }

    @Override
    public AirlineResponseDTO update(AirlineUpdateDTO airlineUpdateDTO, Long id) {
        Airline airlineDb = airlineRepository.findById(id).orElseThrow(() -> new AirlineNotFoundException(id));
        Airline airlineForUpdate = airlineMapper.map(airlineUpdateDTO);
        airlineForUpdate.setId(airlineDb.getId());
        return airlineMapper.map(airlineRepository.save(airlineForUpdate));
    }

    @Override
    public Long delete(Long id) {
        Airline airline = airlineRepository.findById(id).orElseThrow(() -> new AirlineNotFoundException(id));

        List<Flight> flightList = airline.getFlights();
        flightList.forEach(flight -> flightRepository.delete(flight));

        Set<Airport> airportForDeletion = airline.getAirports();
        airportForDeletion.forEach(airport -> airport.getAirlines().remove(airline));

        List<Aircraft> aircraftList = airline.getAircrafts();


      // aircraftRepository.deleteAll(aircraftList);
       aircraftList.forEach(aircraft -> aircraftRepository.delete(aircraft));


        airlineRepository.delete(airline);

        return id;
    }


    @Override
    public AirlineResponseDTO assignAircraftToAirline(Long airlineId, Long aircraftId) {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(() -> new AirlineNotFoundException(airlineId));
        Aircraft aircraft = aircraftRepository.findById(aircraftId).orElseThrow(() -> new AircraftNotFoundException(aircraftId));

        List<Aircraft> aircraftList = airline.getAircrafts();
        aircraftList.add(aircraft);
        airline.setAircrafts(aircraftList);

        return airlineMapper.map(airlineRepository.save(airline));
    }


    @Autowired
    public void setAirlineRepository(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Autowired
    public void setAircraftRepository(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Autowired
    public void setAirlineMapper(AirlineMapper airlineMapper) {
        this.airlineMapper = airlineMapper;
    }

    @Autowired
    public void setAircraftMapper(AircraftMapper aircraftMapper) {
        this.aircraftMapper = aircraftMapper;
    }
}
