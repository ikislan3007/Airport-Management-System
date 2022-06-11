package com.example.demo.domain.models.airport;

import javax.validation.constraints.*;
import java.util.List;

public record AirportCreateDTO(@NotBlank(message = "Airport name cannot be blank")
                               String name,
                               @NotBlank(message = "Airport code cannot be blank")
                               @Size(min=1, max=4)
                               String code,
                               @NotBlank(message = "Address cannot be blank")
                               String address,
                               @NotNull
                               @NotBlank(message="Please enter your phone number")
                               @Pattern(regexp = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
                               String phoneNumber) {
}
