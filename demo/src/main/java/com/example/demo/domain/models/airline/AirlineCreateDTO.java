package com.example.demo.domain.models.airline;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record AirlineCreateDTO(@NotBlank(message = "Name cannot be blank")
                               String name,
                               @URL(message = "Please, enter a valid URL")
                               @NotBlank(message = "Website cannot be blank")
                               String webSite,
                               @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}\\s?[a-zA-Z0-9]{4}\\s?[0-9]{4}\\s?[0-9]{3}([a-zA-Z0-9]\\s?[a-zA-Z0-9]{0,4}\\s?[a-zA-Z0-9]{0,4}\\s?[a-zA-Z0-9]{0,4}\\s?[a-zA-Z0-9]{0,3})?$")
                               @NotBlank
                               String iban,
                               @NotBlank(message = "Insurance company cannot be blank")
                               String insuranceСompany) {
}
