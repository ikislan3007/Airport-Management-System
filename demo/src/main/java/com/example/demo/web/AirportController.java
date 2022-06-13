package com.example.demo.web;

import com.example.demo.domain.models.airport.AirlinesToAirport;
import com.example.demo.domain.models.airport.AirportCreateDTO;
import com.example.demo.domain.models.airport.AirportResponseDTO;
import com.example.demo.domain.models.airport.AirportUpdateDTO;
import com.example.demo.domain.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/airport")
public class AirportController {
    private AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportResponseDTO> create(@Valid @RequestBody AirportCreateDTO airportCreateDTO) {
        return ResponseEntity.ok(airportService.save(airportCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<AirportResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(airportService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AirportUpdateDTO airportUpdateDTO) {
        AirportResponseDTO airportResponseDTO = airportService.update(airportUpdateDTO, id);
        return ResponseEntity.ok(airportResponseDTO);
    }

    @PatchMapping("/airline")
    public ResponseEntity<AirportResponseDTO> assignAirlineToAirport(@Valid@RequestBody AirlinesToAirport airlinesToAirport) {
        AirportResponseDTO airportResponseDTO = airportService.assignAirlineToAirport(airlinesToAirport.airlineId(), airlinesToAirport.airportId());
        return ResponseEntity.ok(airportResponseDTO);
    }
    @Autowired
    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }
}
