package com.example.ecommerse.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.NOT_BLANK_EXCEPTION;
@Getter
@Setter
public class BrandRequestDto {
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String name;
    private List<Long> categoriesId;
}
