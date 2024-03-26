package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.request.CardRequestDto;
import com.example.ecommerse.model.entity.Card;
import com.example.ecommerse.service.cardService.CardService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "status",ignore = true),
            @Mapping(target = "user",ignore = true)
    })
    Card map(CardRequestDto cardRequestDto);
}
