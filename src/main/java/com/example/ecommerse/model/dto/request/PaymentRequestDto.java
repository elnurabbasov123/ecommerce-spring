package com.example.ecommerse.model.dto.request;

import com.example.ecommerse.model.enums.messages.ValidationExceptions;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

import static com.example.ecommerse.model.enums.messages.ValidationExceptions.NOT_BLANK_EXCEPTION;
@Getter
public class PaymentRequestDto {
    @NotBlank(message = NOT_BLANK_EXCEPTION)
    private Long cardId;
}
