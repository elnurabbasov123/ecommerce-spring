package com.example.ecommerse.helper;

import com.example.ecommerse.model.dto.response.ProductsResponseDto;
import com.example.ecommerse.model.entity.Product;

public interface ProductHelperService {
    ProductsResponseDto build(Product product);
}
