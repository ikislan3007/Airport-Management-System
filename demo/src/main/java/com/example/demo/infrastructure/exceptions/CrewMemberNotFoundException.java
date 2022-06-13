package com.example.demo.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CrewMemberNotFoundException extends RuntimeException {
    public CrewMemberNotFoundException(Long id){
        super("Crew member with id " + id + " doesn't exist.");
    }
}

