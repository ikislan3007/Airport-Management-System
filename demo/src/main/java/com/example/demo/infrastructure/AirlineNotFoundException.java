package com.example.demo.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AirlineNotFoundException extends RuntimeException{
    public AirlineNotFoundException(Long id){
        super("Airline with id " + id + " doesn't exist.");
    }
}
