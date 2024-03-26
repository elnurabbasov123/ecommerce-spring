package com.example.ecommerse.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.NOT_BLANK_EXCEPTION;

@Getter
@Setter
public class ProductRequestDto {
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String name;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private int quantity;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private BigDecimal price;
    Long brandId;
    List<Long> categoriesId;
    Map<Long,Map<Long,String>> attributes;
}
