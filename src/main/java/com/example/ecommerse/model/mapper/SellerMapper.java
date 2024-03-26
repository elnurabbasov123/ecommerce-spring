package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.request.SellerRequestDto;
import com.example.ecommerse.model.entity.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "user",ignore = true),
    })
    Seller map(SellerRequestDto requestDto);
}
