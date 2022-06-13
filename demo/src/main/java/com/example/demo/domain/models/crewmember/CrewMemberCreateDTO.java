package com.example.demo.domain.models.crewmember;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

public record CrewMemberCreateDTO(@NotBlank(message = "Field cannot be blank")
                                  @Column(unique = true)
                                  String uniqueIdentifierNumber,

                                  @NotBlank(message = "First Name cannot be blank")
                                  String firstName,

                                  @NotBlank(message = "Last Name cannot be blank")
                                  String lastName,

                                  @NotBlank(message = "Job title cannot be blank")
                                  String jobTitle,

                                  @NotNull
                                  @NotBlank(message = "Please enter your phone number")
                                  @Pattern(regexp = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
                                  String phoneNumber,

                                  @Email
                                  @NotBlank(message = "Email cannot be blank")
                                  String email,

                                  @NotNull
                                  double salary,

                                  @JsonSerialize(using = LocalDateTimeSerializer.class)
                                  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                  LocalDateTime hiringDate,

                                  @NotNull
                                  Long airlineId
) {

}
