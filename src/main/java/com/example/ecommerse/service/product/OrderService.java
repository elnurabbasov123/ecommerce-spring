package com.example.ecommerse.service.product;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface OrderService {
    ResponseEntity<Void> addProductToCart(Long productId);

    ResponseEntity<Void> incrementQuantity(Long productId);

    void createOrder();
}
