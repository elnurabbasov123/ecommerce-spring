package com.example.ecommerse.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CardResponseDto {
    private  Long id;
    private  String cardNumber;
}
