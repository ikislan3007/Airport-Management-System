package com.example.demo.domain.service;

import com.example.demo.domain.models.passenger.PassengerCreateDTO;
import com.example.demo.domain.models.passenger.PassengerResponseDTO;
import com.example.demo.domain.models.passenger.PassengerUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PassengerService {
    PassengerResponseDTO save(PassengerCreateDTO passengerCreateDTO);
    PassengerResponseDTO get(Long id);
    Page<PassengerResponseDTO> getAll(Pageable pageable);
    PassengerResponseDTO update(PassengerUpdateDTO passengerUpdateDTO, Long id);
    Long delete(Long id);
    PassengerResponseDTO addFlightToPassenger(Long passengerId, Long flightId);

}
