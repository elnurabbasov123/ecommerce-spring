package com.example.ecommerse.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.NOT_BLANK_EXCEPTION;
@Getter
@Setter
public class CardRequestDto {
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    @Size(min = 16, max = 16)
    private String cardNumber;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String cvv;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private LocalDate expiredDate;
}
