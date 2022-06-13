package com.example.demo.web;

import com.example.demo.domain.models.crew.CrewCreateDTO;
import com.example.demo.domain.models.crew.CrewResponseDTO;
import com.example.demo.domain.models.crew.CrewUpdateDTO;
import com.example.demo.domain.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/crew")
public class CrewController {

    private CrewService crewService;

    @PostMapping
    public ResponseEntity<CrewResponseDTO> create(@Valid @RequestBody CrewCreateDTO crewCreateDTO) {
        return ResponseEntity.ok(crewService.save(crewCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(crewService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(crewService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<CrewResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(crewService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CrewResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CrewUpdateDTO crewUpdateDTO) {
        CrewResponseDTO crewResponseDTO = crewService.update(crewUpdateDTO, id);
        return ResponseEntity.ok(crewResponseDTO);
    }

    @Autowired
    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }
}
