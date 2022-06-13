package com.example.demo.domain.models.flight;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record FlightUpdateDTO(@NotBlank(message = "Flight number cannot be blank")
                              String flightNumber,

                              @JsonSerialize(using = LocalDateTimeSerializer.class)
                              @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                              LocalDateTime arrTime,

                              @JsonSerialize(using = LocalDateTimeSerializer.class)
                              @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                              LocalDateTime depTime,

                              @NotNull
                              Long deptAirportId,

                              @NotNull
                              Long destAirportId) {

}
