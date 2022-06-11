package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="aircraft")
public class Aircraft extends BaseEntity implements Serializable {
    @Column(unique=true)
    @NotBlank(message = "Registration number cannot be blank")
    @Size(min = 6, max = 6)
    private String registrationNumber;

    @NotBlank(message = "Registration number cannot be blank")
    private String model;

    @NotNull
    private int capacity;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime releaseDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastServiceDate;


//    @ManyToOne(fetch = FetchType.LAZY)
//    private Airline airline;


    public Aircraft(){}
    public Aircraft(String registrationNumber, String model, int capacity, LocalDateTime releaseDate, LocalDateTime lastServiceDate) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.capacity = capacity;
        this.releaseDate = releaseDate;
        this.lastServiceDate = lastServiceDate;
    }



    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDateTime getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(LocalDateTime lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }


//    public Airline getAirline() {
//        return airline;
//    }
//
//    public void setAirline(Airline airline) {
//        this.airline = airline;
//    }


}
