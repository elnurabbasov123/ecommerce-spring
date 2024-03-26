package com.example.ecommerse.model.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.*;
@Getter
@Setter
public class AddressRequestDto {
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String firstAddress;
    private String secondAddress;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private long zipCode;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private long countryId;
}
