package com.example.ecommerse.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryLanguageResponseDto {
    private String name;
    private String description;
    private CategoryResponseDto category;
}
