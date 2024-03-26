package com.example.ecommerse.helper.impl;

import com.example.ecommerse.helper.ProductHelperService;
import com.example.ecommerse.model.dto.response.ProductsResponseDto;
import com.example.ecommerse.model.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductHelperServiceImpl implements ProductHelperService {

    public ProductsResponseDto build(Product product){
        return ProductsResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice().doubleValue())
                .build();
    }
}
