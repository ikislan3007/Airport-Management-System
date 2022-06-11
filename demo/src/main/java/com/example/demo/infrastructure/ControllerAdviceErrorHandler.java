package com.example.demo.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdviceErrorHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onConstraintViolationException(ConstraintViolationException constraintViolationException) {
        List<Violation> violations = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
            violations.add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return new ErrorResponse(violations);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        List<Violation> violations = new ArrayList<>();
        violations.add(new Violation(sqlIntegrityConstraintViolationException.getSQLState(), sqlIntegrityConstraintViolationException.getMessage()));

        return new ErrorResponse(violations);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<Violation> violations = new ArrayList<>();
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            violations.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ErrorResponse(violations);
    }

    @ExceptionHandler({AirportNotFoundException.class, AirlineNotFoundException.class, FlightNotFoundException.class, AircraftNotFoundException.class,PassengerNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Violation handleAccountNotFoundException(RuntimeException exception) {
        return new Violation("id", exception.getMessage());
    }


    @ExceptionHandler( AirportDoesNotServeAirlineException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Violation handleAirportDoesNotServeAirlineException(RuntimeException exception) {
        return new Violation("id", exception.getMessage());
    }


    private record ErrorResponse(List<Violation> violations) {
    }

    private record Violation(String field, String message) {
    }
}

