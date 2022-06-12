package com.example.demo.web;

import com.example.demo.domain.models.crewmember.CrewMemberCreateDTO;
import com.example.demo.domain.models.crewmember.CrewMemberResponseDTO;
import com.example.demo.domain.models.crewmember.CrewMemberUpdateDTO;
import com.example.demo.domain.service.CrewMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/crew/member")
public class CrewMemberController {
    private CrewMemberService crewMemberService;

    @PostMapping
    public ResponseEntity<CrewMemberResponseDTO> create(@Valid @RequestBody CrewMemberCreateDTO crewMemberCreateDTO) {
        return ResponseEntity.ok(crewMemberService.save(crewMemberCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewMemberResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(crewMemberService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(crewMemberService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<CrewMemberResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(crewMemberService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CrewMemberResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CrewMemberUpdateDTO crewMemberUpdateDTO) {
        CrewMemberResponseDTO crewMemberResponseDTO = crewMemberService.update(crewMemberUpdateDTO, id);
        return ResponseEntity.ok(crewMemberResponseDTO);
    }


    @Autowired
    public void setCrewMemberService(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }
}
