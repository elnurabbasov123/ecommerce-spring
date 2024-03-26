package com.example.ecommerse.model.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenResponseDto {
    private  String accessToken;
    private String refreshToken;
}
