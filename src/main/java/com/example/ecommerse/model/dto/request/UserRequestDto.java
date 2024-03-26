package com.example.ecommerse.model.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.*;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String username;
    @Email
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String email;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    @Pattern(regexp = "[a-zA-Z0-9]{6,20}",message = PASSWORD_PATTERN_EXCEPTION)
    private String password;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    @Pattern(regexp = "[0-9]{10,12}",message = PHONE_PATTERN_EXCEPTION)
    private String phone;
}