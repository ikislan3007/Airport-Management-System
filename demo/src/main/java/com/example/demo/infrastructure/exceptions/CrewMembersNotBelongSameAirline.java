package com.example.demo.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CrewMembersNotBelongSameAirline  extends RuntimeException{
    public CrewMembersNotBelongSameAirline() {
        super("Crew members do not belong same aircraft.");
    }
}
