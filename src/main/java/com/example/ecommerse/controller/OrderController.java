package com.example.ecommerse.controller;

import com.example.ecommerse.service.product.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/cart-add/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long productId){
        return orderService.addProductToCart(productId);
    }

    @PostMapping("/incr-quantity/{productId}")
    public ResponseEntity<Void> incrementQuantity(@PathVariable Long productId){
        return orderService.incrementQuantity(productId);
    }
}
