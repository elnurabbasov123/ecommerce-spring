package com.example.ecommerse.controller;

import com.example.ecommerse.model.dto.request.ProductRequestDto;
import com.example.ecommerse.model.dto.request.SellerRequestDto;
import com.example.ecommerse.model.dto.response.ProductsResponseDto;
import com.example.ecommerse.service.product.ProductService;
import com.example.ecommerse.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    private final ProductService productService;
    @PostMapping("/registration")
    public ResponseEntity<Void> registration(@RequestBody SellerRequestDto requestDto) {
        return sellerService.registration(requestDto);
    }

}
