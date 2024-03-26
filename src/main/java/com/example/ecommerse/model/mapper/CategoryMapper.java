package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.response.CategoryLanguageResponseDto;
import com.example.ecommerse.model.entity.CategoryLanguage;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    List<CategoryLanguageResponseDto> map(List<CategoryLanguage> languages);
}
