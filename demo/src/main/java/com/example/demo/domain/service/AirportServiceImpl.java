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
        Set<Airline> airlines = new HashSet<>();
        airportCreateDTO.airlinesId().forEach(id -> airlines.add(airlineRepository.findById(id).orElseThrow(() -> new AirlineNotFoundException(id))));
        airportForSave.setAirlines(airlines);
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
        Set<Airline> airlines = new HashSet<>();
        airportUpdateDTO.airlinesId().forEach(airlinesId -> airlines.add(airlineRepository.findById(airlinesId).orElseThrow(() -> new AirlineNotFoundException(airlinesId))));
        airportForUpdate.setAirlines(airlines);
        return airportMapper.map(airportRepository.save(airportForUpdate));
    }


    @Override
    public Long delete(Long id) {
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
        } else {
            throw new AirportNotFoundException(id);
        }
        return id;
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
