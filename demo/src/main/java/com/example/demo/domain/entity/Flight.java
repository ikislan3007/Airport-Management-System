package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Flight extends BaseEntity implements Serializable {
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airline_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    private Airline airline;


    @ManyToOne
    @JoinColumn(name = "aircraftId")
    private Aircraft aircraft;

    public Flight() {
    }

    public Flight(LocalDateTime arrTime, LocalDateTime depTime, String deptAirportCode, String destAirportCode, Airline airline, Aircraft aircraft) {
        this.arrTime = arrTime;
        this.depTime = depTime;
        this.deptAirportCode = deptAirportCode;
        this.destAirportCode = destAirportCode;
        this.airline = airline;
        this.aircraft = aircraft;
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

}
