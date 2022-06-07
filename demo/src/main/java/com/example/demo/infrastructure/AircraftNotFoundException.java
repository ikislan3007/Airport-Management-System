package com.example.demo.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AircraftNotFoundException extends RuntimeException {
    public AircraftNotFoundException(Long id){
        super("Aircraft with id " + id + " doesn't exist.");
    }
}