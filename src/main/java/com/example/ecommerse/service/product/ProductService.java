package com.example.ecommerse.service.product;

import com.example.ecommerse.model.dto.request.FilterRequestDto;
import com.example.ecommerse.model.dto.request.ProductRequestDto;
import com.example.ecommerse.model.dto.response.*;
import com.example.ecommerse.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;

public interface ProductService {
    ResponseEntity<ProductResponseDto> getById(Long id, Locale locale);

    ResponseEntity<Page<ProductsResponseDto>> getProducts( FilterRequestDto request, int pageNumber, int pageSize);

    ResponseEntity<List<LanguageResponseDto>> getLanguages();

    ResponseEntity<List<BrandResponseDto>> getBrands();

    ResponseEntity<List<CategoryLanguageResponseDto>> getCategories(Locale locale);

    ResponseEntity<ProductsResponseDto> addProduct(ProductRequestDto request);

    Product findById(Long id);
}
