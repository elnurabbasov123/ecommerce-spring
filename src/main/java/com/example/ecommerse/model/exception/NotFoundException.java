package com.example.ecommerse.model.exception;

import com.example.ecommerse.model.enums.messages.Exceptions;
import com.example.ecommerse.model.exception.response.ExceptionResponse;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private final ExceptionResponse exceptionResponse;
    private final Object dynamicKey;

    public NotFoundException(ExceptionResponse exceptionResponse, Object dynamicKey) {
        super(exceptionResponse.getMessage());
        this.exceptionResponse = exceptionResponse;
        this.dynamicKey = dynamicKey;
    }
}
