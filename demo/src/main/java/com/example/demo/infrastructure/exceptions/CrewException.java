package com.example.demo.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CrewException extends RuntimeException{
    public CrewException(){
        super("Before deleting a crew you must assign a new crew to the aircraft");
    }
}