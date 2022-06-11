package com.example.demo.web;

import com.example.demo.domain.models.airline.AircraftToAirline;
import com.example.demo.domain.models.airline.AirlineCreateDTO;
import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.example.demo.domain.models.airline.AirlineUpdateDTO;
import com.example.demo.domain.models.airport.AirlinesToAirport;
import com.example.demo.domain.models.airport.AirportResponseDTO;
import com.example.demo.domain.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/airline")
public class AirlineController {
    private AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponseDTO> create(@Valid @RequestBody AirlineCreateDTO airlineCreateDTO) {
        return ResponseEntity.ok(airlineService.save(airlineCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(airlineService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(airlineService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<AirlineResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(airlineService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AirlineResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AirlineUpdateDTO airlineUpdateDTO) {
        AirlineResponseDTO airlineResponseDTO = airlineService.update(airlineUpdateDTO, id);
        return ResponseEntity.ok(airlineResponseDTO);
    }

    @PatchMapping("/aircraft")
    public ResponseEntity<AirlineResponseDTO> assignAircraftToAirline(@Valid@RequestBody AircraftToAirline aircraftToAirline) {
        AirlineResponseDTO airlineResponseDTO = airlineService.assignAircraftToAirline(aircraftToAirline.airlineId(), aircraftToAirline.aircraftId());
        return ResponseEntity.ok(airlineResponseDTO);
    }

    @Autowired
    public void setAirlineService(AirlineService airlineService) {
        this.airlineService = airlineService;
    }
}
