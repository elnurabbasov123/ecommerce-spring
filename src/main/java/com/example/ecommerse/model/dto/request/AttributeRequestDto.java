package com.example.ecommerse.model.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class AttributeRequestDto {
    Map<Long,String> attributes;
}
