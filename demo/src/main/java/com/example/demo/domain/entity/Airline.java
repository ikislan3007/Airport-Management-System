package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "airline")
public class Airline extends BaseEntity implements Serializable {
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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JsonIgnore
    private Set<Airport> airports = new HashSet<>();


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

}
