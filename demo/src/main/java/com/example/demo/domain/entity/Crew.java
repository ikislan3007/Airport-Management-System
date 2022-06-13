package com.example.demo.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Crew  extends BaseEntity implements Serializable {
    @NotBlank(message = "Field cannot be blank")
    @Column(unique=true)
    private String uniqueCrewIdentifierNumber;

    @OneToMany
    @JoinColumn(name = "crew_id")
    private List<CrewMember> crewMembers=new ArrayList<>();

    public Crew() {
    }

    public Crew(String uniqueCrewIdentifierNumber, List<CrewMember> crewMembers) {
        this.uniqueCrewIdentifierNumber = uniqueCrewIdentifierNumber;
        this.crewMembers = crewMembers;
    }

    public String getUniqueCrewIdentifierNumber() {
        return uniqueCrewIdentifierNumber;
    }

    public void setUniqueCrewIdentifierNumber(String uniqueCrewIdentifierNumber) {
        this.uniqueCrewIdentifierNumber = uniqueCrewIdentifierNumber;
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }
}
