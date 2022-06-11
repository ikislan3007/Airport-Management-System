package com.example.demo.domain.service;

import com.example.demo.domain.entity.Aircraft;
import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.mapper.AircraftMapper;
import com.example.demo.domain.models.aircarft.AircraftCreateDTO;
import com.example.demo.domain.models.aircarft.AircraftResponseDTO;
import com.example.demo.domain.models.aircarft.AircraftUpdateDTO;
import com.example.demo.domain.repository.AircraftRepository;
import com.example.demo.domain.repository.AirlineRepository;
import com.example.demo.domain.repository.FlightRepository;
import com.example.demo.infrastructure.AircraftNotFoundException;
import com.example.demo.infrastructure.AirlineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AircraftServiceImpl implements AircraftService {
    private AircraftRepository aircraftRepository;
    private AircraftMapper aircraftMapper;
    private AirlineRepository airlineRepository;
    private FlightRepository flightRepository;


    @Override
    public AircraftResponseDTO save(AircraftCreateDTO aircraftCreateDTO) {
        Aircraft aircraftForSave = aircraftMapper.map(aircraftCreateDTO);
        Aircraft aircraftSaved = aircraftRepository.save(aircraftForSave);
        return aircraftMapper.map(aircraftSaved);
    }

    @Override
    public AircraftResponseDTO get(Long id) {
        return aircraftMapper.map(
                aircraftRepository.findById(id)
                        .orElseThrow(() -> new AircraftNotFoundException(id)));
    }

    @Override
    public Page<AircraftResponseDTO> getAll(Pageable pageable) {
        return aircraftRepository
                .findAll(pageable)
                .map(aircraftMapper::map);
    }

    @Override
    public AircraftResponseDTO update(AircraftUpdateDTO aircraftUpdateDTO, Long id) {
        Aircraft aircraftDb = aircraftRepository.findById(id).orElseThrow(() -> new AircraftNotFoundException(id));
        Aircraft aircraftForUpdate = aircraftMapper.map(aircraftUpdateDTO);
        aircraftForUpdate.setId(aircraftDb.getId());
        aircraftForUpdate.setModel(aircraftDb.getModel());
        aircraftForUpdate.setReleaseDate(aircraftDb.getReleaseDate());
        return aircraftMapper.map(aircraftRepository.save(aircraftForUpdate));
    }

    @Override
    public Long delete(Long id) {
        if (aircraftRepository.existsById(id)) {
            aircraftRepository.deleteById(id);
        } else {
            throw new AircraftNotFoundException(id);
        }
        return id;
    }

    @Autowired
    public void setAircraftRepository(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Autowired
    public void setAirlineRepository(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Autowired
    public void setAircraftMapper(AircraftMapper aircraftMapper) {
        this.aircraftMapper = aircraftMapper;
    }

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
