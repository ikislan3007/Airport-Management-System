package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Flight extends BaseEntity implements Serializable {
    @Column(unique = true)
    @NotBlank(message = "Flight number cannot be blank")
    private String flightNumber;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime arrTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime depTime;

    @NotBlank(message = "Code cannot be blank")
    @Size(min = 1, max = 4)
    private String deptAirportCode;

    @NotBlank(message = "Code cannot be blank")
    @Size(min = 0, max = 3)
    private String destAirportCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "aircraftId")
    private Aircraft aircraft;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "flights",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    Set<Passenger> passengers=new HashSet<>();

    public Flight() {
    }

//    public Flight(LocalDateTime arrTime, LocalDateTime depTime, String deptAirportCode, String destAirportCode, Airline airline, Aircraft aircraft) {
//        this.arrTime = arrTime;
//        this.depTime = depTime;
//        this.deptAirportCode = deptAirportCode;
//        this.destAirportCode = destAirportCode;
//        this.airline = airline;
//        this.aircraft = aircraft;
//    }


    public Flight(String flightNumber, LocalDateTime arrTime, LocalDateTime depTime, String deptAirportCode, String destAirportCode, Airline airline, Aircraft aircraft, Set<Passenger> passengers) {
        this.flightNumber = flightNumber;
        this.arrTime = arrTime;
        this.depTime = depTime;
        this.deptAirportCode = deptAirportCode;
        this.destAirportCode = destAirportCode;
        this.airline = airline;
        this.aircraft = aircraft;
        this.passengers = passengers;
    }

    public LocalDateTime getArrTime() {
        return arrTime;
    }

    public void setArrTime(LocalDateTime arrTime) {
        this.arrTime = arrTime;
    }

    public LocalDateTime getDepTime() {
        return depTime;
    }

    public void setDepTime(LocalDateTime depTime) {
        this.depTime = depTime;
    }

    public String getDeptAirportCode() {
        return deptAirportCode;
    }

    public void setDeptAirportCode(String deptAirportCode) {
        this.deptAirportCode = deptAirportCode;
    }

    public String getDestAirportCode() {
        return destAirportCode;
    }

    public void setDestAirportCode(String destAirportCode) {
        this.destAirportCode = destAirportCode;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }
}
