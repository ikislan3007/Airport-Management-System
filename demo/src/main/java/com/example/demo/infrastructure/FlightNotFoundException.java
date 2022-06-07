package com.example.demo.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(Long id){
        super("Flight with id " + id + " doesn't exist.");
    }
}

