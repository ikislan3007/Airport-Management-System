package com.example.demo.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CrewNotFoundException extends RuntimeException {
    public CrewNotFoundException(Long id) {
        super("Crew  with id " + id + " doesn't exist.");
    }
}
