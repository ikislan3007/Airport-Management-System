package com.example.demo.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(Long id){
        super("Airport with id " + id + " doesn't exist.");
    }
}
