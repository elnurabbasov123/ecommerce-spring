package com.example.ecommerse.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.NOT_BLANK_EXCEPTION;
@Getter
@Setter
public class LanguageRequestDto {
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String value;
}
