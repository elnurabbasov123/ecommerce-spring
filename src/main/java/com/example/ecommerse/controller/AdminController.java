package com.example.ecommerse.controller;

import com.example.ecommerse.model.dto.request.AttributeRequestDto;
import com.example.ecommerse.model.dto.request.BrandRequestDto;
import com.example.ecommerse.model.dto.request.CategoryRequestDto;
import com.example.ecommerse.model.dto.request.LanguageRequestDto;
import com.example.ecommerse.model.dto.response.CategoryResponseDto;
import com.example.ecommerse.service.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        return adminService.deleteUser(id);
    }

    @PutMapping("/seller-confirmation/{id}")
    public ResponseEntity<Void> confirmSeller(@PathVariable Long id){
        return adminService.confirmSeller(id);
    }

    @PostMapping("/attribute")
    public ResponseEntity<Void> addAttribute(@RequestBody @Valid AttributeRequestDto requestDto){
       return adminService.addAttribute(requestDto);
    }
    @PostMapping("/brand")
    public ResponseEntity<Void> addBrand(@RequestBody @Valid BrandRequestDto requestDto){
        return adminService.addBrand(requestDto);
    }
    @PostMapping("/language")
    public ResponseEntity<Void> addLanguage(@RequestBody @Valid LanguageRequestDto requestDto){
        return adminService.addLanguage(requestDto);
    }
    @PostMapping("/category")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryRequestDto requestDto){
        return adminService.addCategory(requestDto);
    }
}
