package com.example.demo.domain.models.crewmember;

import com.example.demo.domain.models.airline.AirlineResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

public record CrewMemberResponseDTO(Long id,
                                    String uniqueIdentifierNumber,
                                    String firstName,
                                    String lastName,
                                    String jobTitle,
                                    String phoneNumber,
                                    String email,
                                    double salary,
                                    LocalDateTime hiringDate,
                                    @JsonIgnoreProperties({"iban", "insuranceСompany", "aircrafts"})
                                    AirlineResponseDTO airline) {
}
