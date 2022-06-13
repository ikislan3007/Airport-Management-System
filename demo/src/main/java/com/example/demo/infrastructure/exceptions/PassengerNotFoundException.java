package com.example.demo.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PassengerNotFoundException extends RuntimeException{
    public PassengerNotFoundException(Long id){
        super("Passenger with id " + id + " doesn't exist.");
    }
}