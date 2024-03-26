package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.request.CustomerRequestDto;
import com.example.ecommerse.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "addressRequestDto",target = "address",ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "user",ignore = true)
    Customer map(CustomerRequestDto customerRequestDto);
}
