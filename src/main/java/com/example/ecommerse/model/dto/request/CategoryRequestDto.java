package com.example.ecommerse.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.NOT_BLANK_EXCEPTION;
@Setter
@Getter
public class CategoryRequestDto {
    Map<Long,String> name;
    Map<Long,String> description;
}
