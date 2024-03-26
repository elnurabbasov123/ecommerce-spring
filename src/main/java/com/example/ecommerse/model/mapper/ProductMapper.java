package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.request.ProductRequestDto;
import com.example.ecommerse.model.dto.response.ProductResponseDto;
import com.example.ecommerse.model.dto.response.ProductsResponseDto;
import com.example.ecommerse.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categories", ignore = true)
    @Mapping(source = "productAttributes", target = "attributeResponseDto", ignore = true)
    ProductResponseDto map(Product product);
    ProductsResponseDto maps(Product product);

    @Mappings({
            @Mapping(target = "brand", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "productAttributes", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Product map(ProductRequestDto product);

    List<ProductsResponseDto> map(Page<Product> products);
}
