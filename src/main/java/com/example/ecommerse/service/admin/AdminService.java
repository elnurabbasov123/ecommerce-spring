package com.example.ecommerse.service.admin;

import com.example.ecommerse.model.dto.request.AttributeRequestDto;
import com.example.ecommerse.model.dto.request.BrandRequestDto;
import com.example.ecommerse.model.dto.request.CategoryRequestDto;
import com.example.ecommerse.model.dto.request.LanguageRequestDto;
import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.type.NotificationType;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<Void> deleteUser(Long id);


    ResponseEntity<Void> confirmSeller(Long id);

    ResponseEntity<Void> addAttribute(AttributeRequestDto requestDto);

    ResponseEntity<Void> addBrand(BrandRequestDto requestDto);

    ResponseEntity<Void> addLanguage(LanguageRequestDto requestDto);

    ResponseEntity<Void> addCategory(CategoryRequestDto requestDto);
}
