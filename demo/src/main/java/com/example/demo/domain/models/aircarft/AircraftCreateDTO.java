package com.example.demo.domain.models.aircarft;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public record AircraftCreateDTO(@NotBlank(message = "Registration number cannot be blank")
                                @Size(min = 6, max = 6)
                                String registrationNumber,

                                @NotBlank(message = "Registration number cannot be blank")
                                String model,

                                @NotNull
                                int capacity,

                                @JsonSerialize(using = LocalDateTimeSerializer.class)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                                LocalDateTime releaseDate,

                                @JsonSerialize(using = LocalDateTimeSerializer.class)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                                LocalDateTime lastServiceDate

) {
}
