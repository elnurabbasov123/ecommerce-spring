package com.example.ecommerse.model.dto.response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int quantity;
    private double price;
    private List<CategoryLanguageResponseDto> categories;
    private BrandResponseDto brand;
    private AttributeResponseDto attributeResponseDto;
}
