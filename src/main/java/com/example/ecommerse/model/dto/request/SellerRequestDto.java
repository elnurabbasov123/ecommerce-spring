package com.example.ecommerse.model.dto.request;

import com.example.ecommerse.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.NOT_BLANK_EXCEPTION;
@Getter
@Setter
public class SellerRequestDto {
    private String name;
    private String surname;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String serialNumber;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String fin;
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private String livingAddress;

}