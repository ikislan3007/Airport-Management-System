package com.example.demo.domain.models.crew;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record CrewUpdateDTO(@NotBlank(message = "Crew number cannot be blank")
String uniqueCrewIdentifierNumber,
                            @NotNull
                            List<Long> crewMembersID) {
}
