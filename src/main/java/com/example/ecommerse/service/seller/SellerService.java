package com.example.ecommerse.service.seller;

import com.example.ecommerse.model.dto.request.SellerRequestDto;
import com.example.ecommerse.model.entity.Seller;
import org.springframework.http.ResponseEntity;

public interface SellerService {
    ResponseEntity<Void> registration(SellerRequestDto requestDto);

    Seller findByUser(Long id);
}
