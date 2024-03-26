package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.response.BrandResponseDto;
import com.example.ecommerse.model.entity.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponseDto map(Brand brand);
    List<BrandResponseDto> map(List<Brand> brands);
}
