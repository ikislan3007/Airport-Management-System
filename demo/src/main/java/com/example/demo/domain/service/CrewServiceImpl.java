package com.example.demo.domain.service;

import com.example.demo.domain.entity.Crew;
import com.example.demo.domain.entity.CrewMember;
import com.example.demo.domain.mapper.CrewMapper;
import com.example.demo.domain.models.crew.CrewCreateDTO;
import com.example.demo.domain.models.crew.CrewResponseDTO;
import com.example.demo.domain.models.crew.CrewUpdateDTO;
import com.example.demo.domain.repository.AircraftRepository;
import com.example.demo.domain.repository.CrewMemberRepository;
import com.example.demo.domain.repository.CrewRepository;
import com.example.demo.infrastructure.exceptions.CrewException;
import com.example.demo.infrastructure.exceptions.CrewMemberNotFoundException;
import com.example.demo.infrastructure.exceptions.CrewMembersNotBelongSameAirline;
import com.example.demo.infrastructure.exceptions.CrewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CrewServiceImpl implements CrewService {
    private CrewRepository crewRepository;
    private CrewMemberRepository crewMemberRepository;
    private AircraftRepository aircraftRepository;
    private CrewMapper crewMapper;


    @Override
    public CrewResponseDTO save(CrewCreateDTO crewCreateDTO) {
        Crew crewForSave = crewMapper.map(crewCreateDTO);
        List<CrewMember> crewMembers = new ArrayList<>();
        crewCreateDTO.crewMembersID().forEach(id -> crewMembers.add(crewMemberRepository.findById(id).orElseThrow(() -> new CrewMemberNotFoundException(id))));
        checkIfCrewMembersBelongSameAirline(crewMembers);
        crewForSave.setCrewMembers(crewMembers);
        Crew crewSaved = crewRepository.save(crewForSave);
        return crewMapper.map(crewSaved);
    }

    public boolean checkIfCrewMembersBelongSameAirline(List<CrewMember> membersList) {
        Set<Long> setOfAirlines = new HashSet<Long>();
        for (CrewMember crewMember : membersList) {
            setOfAirlines.add(crewMember.getAirline().getId());
        }
        if (setOfAirlines.size() != 1) {
            throw new CrewMembersNotBelongSameAirline();
        }
        return true;
    }

    @Override
    public CrewResponseDTO get(Long id) {
        return crewMapper.map(
                crewRepository.findById(id)
                        .orElseThrow(() -> new CrewNotFoundException(id)));
    }

    @Override
    public Page<CrewResponseDTO> getAll(Pageable pageable) {
        return crewRepository
                .findAll(pageable)
                .map(crewMapper::map);
    }

    @Override
    public CrewResponseDTO update(CrewUpdateDTO crewUpdateDTO, Long id) {
        Crew crewDb = crewRepository.findById(id).orElseThrow(() -> new CrewNotFoundException(id));
        List<CrewMember> crewMembers = new ArrayList<>();
        crewUpdateDTO.crewMembersID().forEach(crewMemberId -> crewMembers.add(crewMemberRepository.findById(crewMemberId).orElseThrow(() -> new CrewMemberNotFoundException(crewMemberId))));
        Crew crewForUpdate = crewMapper.map(crewUpdateDTO);
        crewForUpdate.setId(crewDb.getId());
        checkIfCrewMembersBelongSameAirline(crewMembers);
        crewForUpdate.setCrewMembers(crewMembers);
        return crewMapper.map(crewRepository.save(crewForUpdate));
    }

    @Override
    public Long delete(Long id) {
        Crew crew = crewRepository.findById(id).orElseThrow(() -> new CrewNotFoundException(id));
        if (aircraftRepository.existsByCrewId(crew.getId())) {
            throw new CrewException();
        }
        crewRepository.deleteById(id);

        return id;
    }


    @Autowired
    public void setCrewRepository(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @Autowired
    public void setCrewMapper(CrewMapper crewMapper) {
        this.crewMapper = crewMapper;
    }

    @Autowired
    public void setCrewMemberRepository(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    @Autowired
    public void setAircraftRepository(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }
}
