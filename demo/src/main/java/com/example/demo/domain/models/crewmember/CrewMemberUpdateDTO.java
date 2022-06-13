package com.example.demo.domain.models.crewmember;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record CrewMemberUpdateDTO(@NotBlank(message = "Field cannot be blank")
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
                                  double salary) {
}
