package com.example.demo.domain.service;

import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.mapper.AirportMapper;
import com.example.demo.domain.models.airport.AirportCreateDTO;
import com.example.demo.domain.models.airport.AirportResponseDTO;
import com.example.demo.domain.models.airport.AirportUpdateDTO;
import com.example.demo.domain.repository.AirlineRepository;
import com.example.demo.domain.repository.AirportRepository;
import com.example.demo.infrastructure.AirlineNotFoundException;
import com.example.demo.infrastructure.AirportNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AirportServiceImpl implements AirportService {
    private AirportRepository airportRepository;
    private AirportMapper airportMapper;
    private AirlineRepository airlineRepository;

    @Override
    public AirportResponseDTO save(AirportCreateDTO airportCreateDTO) {
        Airport airportForSave = airportMapper.map(airportCreateDTO);
        Airport airportSaved = airportRepository.save(airportForSave);
        return airportMapper.map(airportSaved);
    }

    @Override
    public AirportResponseDTO get(Long id) {
        return airportMapper.map(
                airportRepository.findById(id)
                        .orElseThrow(() -> new AirportNotFoundException(id)));
    }


    @Override
    public Page<AirportResponseDTO> getAll(Pageable pageable) {
        return airportRepository
                .findAll(pageable)
                .map(airportMapper::map);
    }

    @Override
    public AirportResponseDTO update(AirportUpdateDTO airportUpdateDTO, Long id) {
        Airport airportDb = airportRepository.findById(id).orElseThrow(() -> new AirportNotFoundException(id));
        Airport airportForUpdate = airportMapper.map(airportUpdateDTO);
        airportForUpdate.setId(airportDb.getId());
        airportForUpdate.setCode(airportDb.getCode());
        airportForUpdate.setAirlines(airportDb.getAirlines());
        return airportMapper.map(airportRepository.save(airportForUpdate));
    }


    @Override
    public Long delete(Long id) {
        Airport airport=airportRepository.findById(id).orElseThrow(() -> new AirportNotFoundException(id));
        airport.getAirlines().removeAll(airport.getAirlines());
        airportRepository.delete(airport);
        return id;
    }

    @Override
    public AirportResponseDTO assignAirlineToAirport(Long airlineId, Long airportId) {
        Airline airline=airlineRepository.findById(airlineId).orElseThrow(() -> new AirlineNotFoundException(airlineId));
        Airport airport =airportRepository.findById(airportId).orElseThrow(() -> new AirportNotFoundException(airportId));
        Set<Airline> airlines=new HashSet<>();
        airport.getAirlines().forEach(airline1 -> airlines.add(airline1));
        airlines.add(airline);
        airport.setAirlines(airlines);
        return airportMapper.map(airportRepository.save(airport));

    }

    @Autowired
    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Autowired
    public void setAirportMapper(AirportMapper airportMapper) {
        this.airportMapper = airportMapper;
    }

    @Autowired
    public void setAirlineRepository(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }
}
