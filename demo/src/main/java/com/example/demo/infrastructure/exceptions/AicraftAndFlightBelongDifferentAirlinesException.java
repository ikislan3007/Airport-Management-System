package com.example.demo.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AicraftAndFlightBelongDifferentAirlinesException  extends RuntimeException{
    public AicraftAndFlightBelongDifferentAirlinesException(){
        super("Crew and Aircraft  belong different airlines.");
    }
}
