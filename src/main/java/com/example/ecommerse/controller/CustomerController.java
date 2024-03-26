package com.example.ecommerse.controller;

import com.example.ecommerse.model.dto.request.CustomerRequestDto;
import com.example.ecommerse.service.user.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/registration")
    public ResponseEntity<Void> registerCustomer(@RequestBody CustomerRequestDto requestDto){
        return customerService.register(requestDto);
    }
}
