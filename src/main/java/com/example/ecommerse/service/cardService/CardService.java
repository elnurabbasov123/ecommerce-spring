package com.example.ecommerse.service.cardService;

import com.example.ecommerse.model.dto.request.CardRequestDto;
import com.example.ecommerse.model.dto.request.PaymentRequestDto;
import com.example.ecommerse.model.dto.response.CardResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardService {
    ResponseEntity<Void> add(CardRequestDto requestDto);

    ResponseEntity<List<CardResponseDto>> getAll();

    ResponseEntity<Void> payment(PaymentRequestDto requestDto);
}
