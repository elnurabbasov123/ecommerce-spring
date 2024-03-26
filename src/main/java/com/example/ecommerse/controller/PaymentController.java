package com.example.ecommerse.controller;

import com.example.ecommerse.model.dto.request.PaymentRequestDto;
import com.example.ecommerse.service.cardService.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final CardService cardService;
    @GetMapping
    public ResponseEntity<Void> payment(@RequestBody PaymentRequestDto requestDto){
        return cardService.payment(requestDto);
    }
}
