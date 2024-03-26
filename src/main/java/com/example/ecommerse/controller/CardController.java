package com.example.ecommerse.controller;

import com.example.ecommerse.model.dto.request.CardRequestDto;
import com.example.ecommerse.model.dto.response.CardResponseDto;
import com.example.ecommerse.service.cardService.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody CardRequestDto requestDto) {
        return cardService.add(requestDto);
    }
    @GetMapping
    public ResponseEntity<List<CardResponseDto>> getAll(){
        return cardService.getAll();
    }
}
