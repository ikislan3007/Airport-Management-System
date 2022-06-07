package com.example.demo.domain.service;

import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.mapper.AirlineMapper;
import com.example.demo.domain.models.airline.AirlineCreateDTO;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.airline.AirlineUpdateDTO;
import com.example.demo.domain.repository.AirlineRepository;
import com.example.demo.infrastructure.AirlineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl implements AirlineService {
    private AirlineRepository airlineRepository;
    private AirlineMapper airlineMapper;

    @Override
    public AirlineResponseDTO save(AirlineCreateDTO airlineCreateDTO) {
        Airline airlineForSave = airlineMapper.map(airlineCreateDTO);
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
        if (airlineRepository.existsById(id)) {
            airlineRepository.deleteById(id);
        } else {
            throw new AirlineNotFoundException(id);
        }
        return id;
    }

    @Autowired
    public void setAirlineRepository(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Autowired
    public void setAirlineMapper(AirlineMapper airlineMapper) {
        this.airlineMapper = airlineMapper;
    }
}
