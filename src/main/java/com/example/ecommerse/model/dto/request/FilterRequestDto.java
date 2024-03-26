package com.example.ecommerse.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterRequestDto {
    private String name;
    private Double firstPrice;
    private Double secondPrice;
    private Long brandId;
    private Long categoryId;
}
