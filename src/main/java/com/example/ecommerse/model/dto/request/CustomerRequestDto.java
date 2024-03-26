package com.example.ecommerse.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.NOT_BLANK_EXCEPTION;
@Getter
@Setter
public class CustomerRequestDto {
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String name;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String surname;
    @Past
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private LocalDate birthDate;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private AddressRequestDto addressRequestDto;
}
