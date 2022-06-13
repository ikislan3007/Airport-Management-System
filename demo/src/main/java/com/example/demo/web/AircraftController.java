package com.example.demo.web;

import com.example.demo.domain.models.aircarft.AircraftCreateDTO;
import com.example.demo.domain.models.aircarft.AircraftResponseDTO;
import com.example.demo.domain.models.aircarft.AircraftUpdateDTO;
import com.example.demo.domain.models.aircarft.CrewToAircraft;
import com.example.demo.domain.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    private AircraftService aircraftService;

    @PostMapping
    public ResponseEntity<AircraftResponseDTO> create(@Valid @RequestBody AircraftCreateDTO aircraftCreateDTO) {
        return ResponseEntity.ok(aircraftService.save(aircraftCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AircraftResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(aircraftService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(aircraftService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<AircraftResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(aircraftService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AircraftResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AircraftUpdateDTO aircraftUpdateDTO) {
        AircraftResponseDTO aircraftResponseDTO = aircraftService.update(aircraftUpdateDTO, id);
        return ResponseEntity.ok(aircraftResponseDTO);
    }

    @PatchMapping("/crew")
    public ResponseEntity<AircraftResponseDTO> assignCrewToAircraft(@Valid@RequestBody CrewToAircraft crewToAircraft) {
        AircraftResponseDTO aircraftResponseDTO  = aircraftService.assignCrewToAircraft(crewToAircraft.crewId(), crewToAircraft.aircraftId());
        return ResponseEntity.ok(aircraftResponseDTO);
    }

    @Autowired
    public void setAircraftService(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }
}
