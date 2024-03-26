package com.example.ecommerse.model.mapper;

import com.example.ecommerse.model.dto.request.UserRequestDto;
import com.example.ecommerse.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "role",ignore = true),
            @Mapping(target = "enabled",ignore = true),
            @Mapping(target = "nonLocked",ignore = true)
    })
    User map(UserRequestDto userRequestDto);
}
