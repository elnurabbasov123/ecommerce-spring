package com.example.ecommerse.model.exception;

import com.example.ecommerse.model.exception.response.ExceptionResponse;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final ExceptionResponse exceptionResponse;

    public ApplicationException(ExceptionResponse exceptionResponse) {
        super(exceptionResponse.getMessage());
        this.exceptionResponse = exceptionResponse;
    }
}
