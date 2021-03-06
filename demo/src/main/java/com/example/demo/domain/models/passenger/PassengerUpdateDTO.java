package com.example.demo.domain.models.passenger;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public record PassengerUpdateDTO(@NotBlank(message = "First Name cannot be blank")
                                 String firstName,

                                 @NotBlank(message = "Last Name cannot be blank")
                                 String lastName,

                                 @Email
                                 @NotBlank(message = "Email cannot be blank")
                                 String email,

                                 @NotNull
                                 @NotBlank(message = "Please enter your phone number")
                                 @Pattern(regexp = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
                                 String phoneNumber,

                                 @JsonSerialize(using = LocalDateTimeSerializer.class)
                                 @JsonFormat(pattern = "yyyy-MM-dd")
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                 Date birthDate,

                                 @NotBlank(message = "Field cannot be blank")
                                 String uniqueIdentifierNumber) {
}
