package com.example.ecommerse.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class AttributeResponseDto {
    private Map<String, String> attributes;
}
