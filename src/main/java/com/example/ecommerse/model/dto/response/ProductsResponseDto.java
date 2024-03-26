package com.example.ecommerse.model.dto.response;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponseDto {
    private Long id;
    private String name;
    private double price;
}
