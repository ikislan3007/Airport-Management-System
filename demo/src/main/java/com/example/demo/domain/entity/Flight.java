package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport deptAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport destAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Airline airline;

    @ManyToOne
    private Aircraft deptAircraft;

    @ManyToOne
    private Aircraft destAircraft;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "flights",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    Set<Passenger> passengers=new HashSet<>();

    public Flight() {
    }

    public Flight(String flightNumber, LocalDateTime arrTime, LocalDateTime depTime, Airport deptAirport, Airport destAirport, Airline airline, Aircraft deptAircraft, Aircraft destAircraft, Set<Passenger> passengers) {
        this.flightNumber = flightNumber;
        this.arrTime = arrTime;
        this.depTime = depTime;
        this.deptAirport = deptAirport;
        this.destAirport = destAirport;
        this.airline = airline;
        this.deptAircraft = deptAircraft;
        this.destAircraft = destAircraft;
        this.passengers = passengers;
    }

    public Aircraft getDeptAircraft() {
        return deptAircraft;
    }

    public void setDeptAircraft(Aircraft deptAircraft) {
        this.deptAircraft = deptAircraft;
    }

    public Aircraft getDestAircraft() {
        return destAircraft;
    }

    public void setDestAircraft(Aircraft destAircraft) {
        this.destAircraft = destAircraft;
    }

    public Airport getDeptAirport() {
        return deptAirport;
    }

    public void setDeptAirport(Airport deptAirport) {
        this.deptAirport = deptAirport;
    }

    public Airport getDestAirport() {
        return destAirport;
    }

    public void setDestAirport(Airport destAirport) {
        this.destAirport = destAirport;
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

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
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


    public void setAircraft(Aircraft aircraft) {
    }
}
