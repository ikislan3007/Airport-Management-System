package com.example.demo.domain.service;

import com.example.demo.domain.entity.Aircraft;
import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.entity.Crew;
import com.example.demo.domain.entity.CrewMember;
import com.example.demo.domain.mapper.AircraftMapper;
import com.example.demo.domain.models.aircarft.AircraftCreateDTO;
import com.example.demo.domain.models.aircarft.AircraftResponseDTO;
import com.example.demo.domain.models.aircarft.AircraftUpdateDTO;
import com.example.demo.domain.repository.AircraftRepository;
import com.example.demo.domain.repository.AirlineRepository;
import com.example.demo.domain.repository.CrewRepository;
import com.example.demo.infrastructure.exceptions.AircraftNotFoundException;
import com.example.demo.infrastructure.exceptions.AirlineNotFoundException;
import com.example.demo.infrastructure.exceptions.CrewAndAircraftNotBelongSameAirlineException;
import com.example.demo.infrastructure.exceptions.CrewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {
    private AircraftRepository aircraftRepository;
    private AircraftMapper aircraftMapper;
    private CrewRepository crewRepository;
    private AirlineRepository airlineRepository;


    @Override
    public AircraftResponseDTO save(AircraftCreateDTO aircraftCreateDTO) {
        Aircraft aircraftForSave = aircraftMapper.map(aircraftCreateDTO);
        Airline airline=airlineRepository.findById(aircraftCreateDTO.airlineId()).orElseThrow(() -> new AirlineNotFoundException(aircraftCreateDTO.airlineId()));
        aircraftForSave.setAirline(airline);
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
        aircraftForUpdate.setAirline(aircraftDb.getAirline());
        aircraftForUpdate.setCrew(aircraftDb.getCrew());
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

    @Override
    public AircraftResponseDTO assignCrewToAircraft(Long crewId, Long aircraftId) {
        Crew crew = crewRepository.findById(crewId).orElseThrow(() -> new CrewNotFoundException(crewId));
        Aircraft aircraft = aircraftRepository.findById(aircraftId).orElseThrow(() -> new AircraftNotFoundException(aircraftId));
        Airline airline=airlineRepository.findByAircraftsId(aircraft.getId());
        List<CrewMember> membersList = crew.getCrewMembers();
        for (CrewMember crewMember : membersList) {
            if (!(crewMember.getAirline().equals(airline))) {
                throw new CrewAndAircraftNotBelongSameAirlineException();
            }
        }
        aircraft.setCrew(crew);
        return aircraftMapper.map(aircraftRepository.save(aircraft));
    }


    @Autowired
    public void setAircraftRepository(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }
    @Autowired
    public void setCrewRepository(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @Autowired
    public void setAircraftMapper(AircraftMapper aircraftMapper) {
        this.aircraftMapper = aircraftMapper;
    }

    @Autowired
    public void setAirlineRepository(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }
}



