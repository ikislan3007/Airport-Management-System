package com.example.demo.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CrewAndAircraftNotBelongSameAirlineException extends RuntimeException {
    public CrewAndAircraftNotBelongSameAirlineException(){
        super("Crew and Aircraft do not belong same airline.");
    }
}