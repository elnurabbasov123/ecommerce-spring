package com.example.ecommerse.model.exception.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class ExceptionResponse {
    private final HttpStatus http;
    private final String message;
    public ExceptionResponse(HttpStatus http, String message) {
        this.http = http;
        this.message = message;
    }
}
