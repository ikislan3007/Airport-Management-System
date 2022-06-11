package com.example.demo.domain.models.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record FlightUpdateDTO(@NotBlank(message = "Flight number cannot be blank")
                              String flightNumber,

                              @JsonSerialize(using = LocalDateTimeSerializer.class)
                              @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                              LocalDateTime arrTime,

                              @JsonSerialize(using = LocalDateTimeSerializer.class)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                              @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                              LocalDateTime depTime,

                              @NotBlank(message = "Code cannot be blank")
                              @Size(min = 1, max = 4)
                              String deptAirportCode,

                              @NotBlank(message = "Code cannot be blank")
                              @Size(min = 0, max = 3)
                              String destAirportCode) {
}
