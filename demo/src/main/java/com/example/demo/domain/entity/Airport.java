package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Airport extends BaseEntity implements Serializable {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(unique=true)
    @NotBlank(message = "Code cannot be blank")
    @Size(min = 1, max = 4)
    private String code;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotNull
    @NotBlank(message = "Please enter your phone number")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "airport_airline",
            joinColumns = {@JoinColumn(name = "airport_id")},
            inverseJoinColumns = {@JoinColumn(name = "airline_id")})
    private Set<Airline> airlines = new HashSet<>();

    public Airport() {
    }

    public Airport(String name, String code, String address, String phoneNumber, Set<Airline> airlines) {
        this.name = name;
        this.code = code;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.airlines = airlines;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(Set<Airline> airlines) {
        this.airlines = airlines;
    }

}
