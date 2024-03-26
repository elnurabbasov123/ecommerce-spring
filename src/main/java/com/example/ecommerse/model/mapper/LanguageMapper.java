package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.response.LanguageResponseDto;
import com.example.ecommerse.model.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    List<LanguageResponseDto> map(List<Language> languages);
}
