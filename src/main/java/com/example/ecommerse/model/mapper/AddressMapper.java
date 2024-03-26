package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.request.AddressRequestDto;
import com.example.ecommerse.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "country",ignore = true)
    Address map(AddressRequestDto addressRequestDto);
}
