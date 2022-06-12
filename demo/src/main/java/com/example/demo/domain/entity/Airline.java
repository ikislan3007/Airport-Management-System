package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "airline")
public class Airline extends BaseEntity implements Serializable {
    @Column(unique = true)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @URL(message = "Please, enter a valid URL")
    @NotBlank(message = "Website cannot be blank")
    private String webSite;

    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}\\s?[a-zA-Z0-9]{4}\\s?[0-9]{4}\\s?[0-9]{3}([a-zA-Z0-9]\\s?[a-zA-Z0-9]{0,4}\\s?[a-zA-Z0-9]{0,4}\\s?[a-zA-Z0-9]{0,4}\\s?[a-zA-Z0-9]{0,3})?$")
    @NotBlank
    private String iban;

    @NotBlank(message = "Insurance company cannot be blank")
    private String insuranceСompany;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "airlines",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    Set<Airport> airports = new HashSet<>();



    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "airline_id")
    List<Aircraft> aircrafts=new ArrayList<>();

//    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "airline_id")
//    List<CrewMember> crewMembers =new ArrayList<>();



    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airline", orphanRemoval = true)
    private List<Flight> flights= new ArrayList<>();

    public Airline() {
    }

    public Airline(String name, String webSite, String iban, String insuranceСompany, Set<Airport> airports, List<Flight> flights) {
        this.name = name;
        this.webSite = webSite;
        this.iban = iban;
        this.insuranceСompany = insuranceСompany;
        this.airports = airports;
        this.flights = flights;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getInsuranceСompany() {
        return insuranceСompany;
    }

    public void setInsuranceСompany(String insuranceСompany) {
        this.insuranceСompany = insuranceСompany;
    }

    public Set<Airport> getAirports() {
        return airports;
    }

    public void setAirports(Set<Airport> airports) {
        this.airports = airports;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }


//    public List<CrewMember> getCrewMembers() {
//        return crewMembers;
//    }
//
//    public void setCrewMembers(List<CrewMember> crewMembers) {
//        this.crewMembers = crewMembers;
//    }
}


