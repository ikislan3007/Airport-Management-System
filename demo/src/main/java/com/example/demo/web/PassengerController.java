package com.example.demo.web;

import com.example.demo.domain.models.passenger.FlightToPassengerDTO;
import com.example.demo.domain.models.passenger.PassengerCreateDTO;
import com.example.demo.domain.models.passenger.PassengerResponseDTO;
import com.example.demo.domain.models.passenger.PassengerUpdateDTO;
import com.example.demo.domain.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("passenger")
public class PassengerController {
    private PassengerService passengerService;

    @PostMapping
    public ResponseEntity<PassengerResponseDTO> create(@Valid @RequestBody PassengerCreateDTO passengerCreateDTO) {
        return ResponseEntity.ok(passengerService.save(passengerCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Page<PassengerResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(passengerService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PassengerResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PassengerUpdateDTO passengerUpdateDTO) {
        PassengerResponseDTO passengerResponseDTO = passengerService.update(passengerUpdateDTO, id);
        return ResponseEntity.ok(passengerResponseDTO);
    }
    @PatchMapping("/flight")
    public ResponseEntity<PassengerResponseDTO> addFlightToPassenger(@Valid@RequestBody FlightToPassengerDTO flightToPassengerDTO) {
        PassengerResponseDTO passengerResponseDTO = passengerService.addFlightToPassenger(flightToPassengerDTO.passengerId(), flightToPassengerDTO.flightId());
        return ResponseEntity.ok(passengerResponseDTO);
    }


    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
}
