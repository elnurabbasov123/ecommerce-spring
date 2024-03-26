package com.example.ecommerse.service.authentication;

import com.example.ecommerse.model.dto.request.LoginRequestDto;
import com.example.ecommerse.model.dto.request.UserRequestDto;
import com.example.ecommerse.model.dto.response.MessageResponseDto;
import com.example.ecommerse.model.dto.response.TokenResponseDto;
import com.example.ecommerse.model.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<MessageResponseDto> register(UserRequestDto userRequestDto);

    ResponseEntity<TokenResponseDto> login(LoginRequestDto loginResponseDto);

    ResponseEntity<MessageResponseDto> confirmation(String otp,String username);

    User getAuthenticatedUser();
}
