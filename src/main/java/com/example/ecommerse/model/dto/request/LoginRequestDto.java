package com.example.ecommerse.model.dto.request;

import com.example.ecommerse.model.enums.messages.ValidationExceptions;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequestDto {
    @NotBlank(message = ValidationExceptions.PASSWORD_PATTERN_EXCEPTION)
    @Email(message = ValidationExceptions.EMAIL_EXCEPTION)
    private  String email;
    @NotBlank(message = ValidationExceptions.PASSWORD_PATTERN_EXCEPTION)
    private  String password;
}
