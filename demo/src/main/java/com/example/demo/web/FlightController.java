package com.example.demo.web;

import com.example.demo.domain.models.flight.AircraftToFlightDTO;
import com.example.demo.domain.models.flight.FlightCreateDTO;
import com.example.demo.domain.models.flight.FlightResponseDTO;
import com.example.demo.domain.models.flight.FlightUpdateDTO;
import com.example.demo.domain.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponseDTO> create(@Valid @RequestBody FlightCreateDTO flightCreateDTO) {
        return ResponseEntity.ok(flightService.save(flightCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<FlightResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(flightService.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> update(@PathVariable Long id, @Valid @RequestBody FlightUpdateDTO flightUpdateDTO) {
        FlightResponseDTO flightResponseDTO = flightService.update(flightUpdateDTO, id);
        return ResponseEntity.ok(flightResponseDTO);
    }
    @PatchMapping("/aircraft")
    public ResponseEntity<FlightResponseDTO> assignAircraftToFlight(@Valid@RequestBody AircraftToFlightDTO aircraftToFlightDTO) {
        FlightResponseDTO flightResponseDTO = flightService.assignAircraftToFlight(aircraftToFlightDTO.aircraftId(), aircraftToFlightDTO.flightId());
        return ResponseEntity.ok(flightResponseDTO);
    }


    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }
}
