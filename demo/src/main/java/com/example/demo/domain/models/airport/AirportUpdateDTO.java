package com.example.demo.domain.models.airport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public record AirportUpdateDTO(@NotBlank(message = "Airport name cannot be blank")
                               String name,
                               @NotBlank(message = "Address cannot be blank")
                               String address,
                               @NotNull
                               @NotBlank(message="Please enter your phone number")
                               @Pattern(regexp = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
                               String phoneNumber){
}
