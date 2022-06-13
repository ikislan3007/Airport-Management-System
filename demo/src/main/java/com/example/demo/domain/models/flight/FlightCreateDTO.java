package com.example.demo.domain.models.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record FlightCreateDTO(@NotBlank(message = "Flight number cannot be blank")
                              String flightNumber,

                              @JsonSerialize(using = LocalDateTimeSerializer.class)
                              @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                              LocalDateTime arrTime,

                              @JsonSerialize(using = LocalDateTimeSerializer.class)
                              @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                              LocalDateTime depTime,

                              @NotNull

                              Long deptAirportId,

                              @NotNull
                              Long destAirportId,

                              @NotNull
                              Long airlineId) {
}
