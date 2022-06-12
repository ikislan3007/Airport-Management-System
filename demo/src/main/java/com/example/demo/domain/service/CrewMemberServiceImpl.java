package com.example.demo.domain.service;

import com.example.demo.domain.entity.Airline;
import com.example.demo.domain.entity.CrewMember;
import com.example.demo.domain.mapper.CrewMemberMapper;
import com.example.demo.domain.models.crewmember.CrewMemberCreateDTO;
import com.example.demo.domain.models.crewmember.CrewMemberResponseDTO;
import com.example.demo.domain.models.crewmember.CrewMemberUpdateDTO;
import com.example.demo.domain.repository.AirlineRepository;
import com.example.demo.domain.repository.CrewMemberRepository;
import com.example.demo.infrastructure.AirlineNotFoundException;
import com.example.demo.infrastructure.CrewMemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CrewMemberServiceImpl implements CrewMemberService {
    private CrewMemberRepository crewMemberRepository;
    private CrewMemberMapper crewMemberMapper;
    private AirlineRepository airlineRepository;


    @Override
    public CrewMemberResponseDTO save(CrewMemberCreateDTO crewMemberCreateDTO) {
        Airline airline=airlineRepository.findById(crewMemberCreateDTO.airlineId()).orElseThrow(() -> new AirlineNotFoundException(crewMemberCreateDTO.airlineId()));

        CrewMember employeeForSave = crewMemberMapper.map(crewMemberCreateDTO);
        employeeForSave.setAirline(airline);

        CrewMember employeeSaved = crewMemberRepository.save(employeeForSave);
        return crewMemberMapper.map(employeeSaved);
    }

    @Override
    public CrewMemberResponseDTO get(Long id) {
        return crewMemberMapper.map(
                crewMemberRepository.findById(id)
                        .orElseThrow(() -> new CrewMemberNotFoundException(id)));
    }

    @Override
    public Page<CrewMemberResponseDTO> getAll(Pageable pageable) {
        return
                crewMemberRepository.findAll(pageable)
                .map(crewMemberMapper::map);
    }

    @Override
    public CrewMemberResponseDTO update(CrewMemberUpdateDTO crewMemberUpdateDTO, Long id) {
        CrewMember crewMember=crewMemberRepository.findById(id).orElseThrow(() -> new CrewMemberNotFoundException(id));

        CrewMember crewMemberForUpdate = crewMemberMapper.map(crewMemberUpdateDTO);
        crewMemberForUpdate.setId(crewMember.getId());
        crewMemberForUpdate.setAirline(crewMember.getAirline());
        crewMemberForUpdate.setHiringDate(crewMember.getHiringDate());
        return crewMemberMapper.map(crewMemberRepository.save(crewMemberForUpdate));

    }

    @Override
    public Long delete(Long id) {
        if (crewMemberRepository.existsById(id)) {
            crewMemberRepository.deleteById(id);
        } else {
            throw new CrewMemberNotFoundException(id);
        }
        return id;
    }

    @Autowired
    public void setCrewMemberRepository(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    @Autowired
    public void setCrewMemberMapper(CrewMemberMapper crewMemberMapper) {
        this.crewMemberMapper = crewMemberMapper;
    }

    @Autowired
    public void setAirlineRepository(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }
}
