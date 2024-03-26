package com.example.ecommerse.controller;

import com.example.ecommerse.model.dto.request.FilterRequestDto;
import com.example.ecommerse.model.dto.request.ProductRequestDto;
import com.example.ecommerse.model.dto.response.*;
import com.example.ecommerse.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id, @RequestHeader(value = "language", required = false, defaultValue = "AZ") Locale locale) {
        return productService.getById(id, locale);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryLanguageResponseDto>> getCategories(@RequestHeader(value = "language") Locale locale) {
        return productService.getCategories(locale);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponseDto>> getBrands() {
        return productService.getBrands();
    }

    @GetMapping()
    public ResponseEntity<Page<ProductsResponseDto>> getProducts(@RequestBody(required = false) FilterRequestDto request,
                                                                 @RequestParam(value = "number", required = false, defaultValue = "0") Integer pageNumber,
                                                                 @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize) {
        return productService.getProducts(request, pageNumber, pageSize);
    }

    @GetMapping("/languages")
    public ResponseEntity<List<LanguageResponseDto>> getLanguages() {
        return productService.getLanguages();
    }

    @PostMapping()
    public ResponseEntity<ProductsResponseDto> addProduct(@RequestBody ProductRequestDto request) {
        return productService.addProduct(request);
    }

}
