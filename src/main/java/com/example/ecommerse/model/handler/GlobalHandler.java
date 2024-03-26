package com.example.ecommerse.model.handler;

import com.example.ecommerse.model.exception.ApplicationException;
import com.example.ecommerse.model.exception.NotFoundException;
import com.example.ecommerse.util.MessageUtil;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalHandler extends DefaultErrorAttributes {
    private final MessageUtil messageUtil;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handle(ApplicationException ex,
                                                      WebRequest webRequest) {
        return of(ex,webRequest);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle(NotFoundException ex,
                                                      WebRequest webRequest) {
        return of(ex,webRequest);
    }

    private Map<String, Object> buildExceptionResponse(String message,
                                                       WebRequest webRequest,
                                                       HttpStatus http) {

        Map<String, Object> errorAttributes = getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        errorAttributes.put("error", message);
        errorAttributes.put("status", http.value());
        errorAttributes.put("timestamp", LocalDateTime.now());
        errorAttributes.put("path", ((ServletRequestAttributes) webRequest).getRequest().getServletPath());

        return errorAttributes;
    }

    private ResponseEntity<Map<String,Object>> of(ApplicationException ex,WebRequest webRequest){
        return new ResponseEntity<>(buildExceptionResponse(ex.getExceptionResponse().getMessage(),webRequest,
                ex.getExceptionResponse().getHttp()),
                ex.getExceptionResponse().getHttp());
    }
    private ResponseEntity<Map<String,Object>> of(NotFoundException ex,WebRequest webRequest){
        return new ResponseEntity<>(buildExceptionResponse(messageUtil.getMessage(ex.getExceptionResponse().getMessage(),ex.getDynamicKey()),
                webRequest,
                ex.getExceptionResponse().getHttp()),ex.getExceptionResponse().getHttp());
    }

}
