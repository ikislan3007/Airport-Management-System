package com.example.demo.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AirportDoesNotServeAirlineException extends RuntimeException {
    public AirportDoesNotServeAirlineException(Long airportId, Long airlineId){
        super("Airport with id " + airportId + " doesn't serve airline with id "+airlineId);
    }
}


