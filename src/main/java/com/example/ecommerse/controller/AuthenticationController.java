package com.example.ecommerse.controller;

import com.example.ecommerse.model.dto.request.LoginRequestDto;
import com.example.ecommerse.model.dto.request.UserRequestDto;
import com.example.ecommerse.model.dto.response.MessageResponseDto;
import com.example.ecommerse.model.dto.response.TokenResponseDto;
import com.example.ecommerse.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/registration")
    ResponseEntity<MessageResponseDto> register(@RequestBody @Valid
                                                UserRequestDto userRequestDto){

        return authenticationService.register(userRequestDto);
    }
    @GetMapping("/confirmation")
    ResponseEntity<MessageResponseDto> confirmation(@RequestParam("otp") String otp,
                                                    @RequestParam("username") String username){

        return authenticationService.confirmation(otp,username);
    }
    @PostMapping("/login")
    ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto request) {
        return authenticationService.login(request);
    }
}
