package com.example.ecommerse.service.product.impl;

import com.example.ecommerse.helper.ProductHelperService;
import com.example.ecommerse.model.dto.request.FilterRequestDto;
import com.example.ecommerse.model.dto.request.ProductRequestDto;
import com.example.ecommerse.model.dto.response.*;
import com.example.ecommerse.model.entity.*;
import com.example.ecommerse.model.enums.messages.Exceptions;
import com.example.ecommerse.model.exception.NotFoundException;
import com.example.ecommerse.model.exception.response.ExceptionResponse;
import com.example.ecommerse.model.mapper.BrandMapper;
import com.example.ecommerse.model.mapper.CategoryMapper;
import com.example.ecommerse.model.mapper.LanguageMapper;
import com.example.ecommerse.model.mapper.ProductMapper;
import com.example.ecommerse.repository.*;
import com.example.ecommerse.repository.specification.ProductSpecification;
import com.example.ecommerse.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.ecommerse.model.enums.messages.Exceptions.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryLanguageRepository categoryLanguageRepository;
    private final CategoryMapper categoryMapper;
    private final ProductAttributeLanguageRepository productAttributeLanguageRepository;
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final ProductHelperService productHelperService;
    private final ProductSpecification productSpecification;


    @Override
    public ResponseEntity<ProductResponseDto> getById(Long id, Locale locale) {
        Product product = productRepository
                .findById(id).orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),NOT_FOUND.getMessage()), id));

        ProductResponseDto productResponseDto = productMapper.map(product);

        List<CategoryLanguage> categoryLanguageList = categoryLanguageRepository.findAll(product.getCategories(), locale.getLanguage().toUpperCase());

        List<CategoryLanguageResponseDto> categoryLanguageResponseDtos = categoryMapper.map(categoryLanguageList);

        productResponseDto.setCategories(categoryLanguageResponseDtos);

        productResponseDto.setAttributeResponseDto(getAttributes(product.getId(), locale).getBody());

        return ResponseEntity.ok().body(productResponseDto);
    }

    @Override
    public ResponseEntity<Page<ProductsResponseDto>> getProducts(FilterRequestDto request, int pageNumber, int pageSize) {
        Specification<Product> specification;

        if (!isAllSearch(request)) {
            specification = productSpecification.hasName(request.getName())
                    .and(productSpecification.hasFirstPrice(request.getFirstPrice()))
                    .and(productSpecification.hasSecondPrice(request.getSecondPrice()))
                    .and(productSpecification.hasBrand(request.getBrandId()))
                    .and(productSpecification.hasCategory(request.getCategoryId()));
        } else {
            specification = productSpecification.none();
        }


        Page<Product> products = productRepository
                .findAll(specification, PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, "price")));

        Page<ProductsResponseDto> productsResponseDtos = products.map(productMapper::maps);

        return ResponseEntity.ok(productsResponseDtos);
    }

    @Override
    public ResponseEntity<List<LanguageResponseDto>> getLanguages() {
        List<Language> languages = languageRepository.findAll();
        List<LanguageResponseDto> languageResponseDtos = languageMapper.map(languages);
        return ResponseEntity.ok(languageResponseDtos);
    }

    @Override
    public ResponseEntity<List<BrandResponseDto>> getBrands() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandResponseDto> brandResponseDtos = brandMapper.map(brands);

        return ResponseEntity.ok(brandResponseDtos);
    }

    @Override
    public ResponseEntity<List<CategoryLanguageResponseDto>> getCategories(Locale locale) {
        List<CategoryLanguage> allByLanguageValue =
                categoryLanguageRepository.findAllByLanguage_Value(locale.getLanguage().toUpperCase());

        List<CategoryLanguageResponseDto> categoryLanguageResponseDtos = categoryMapper.map(allByLanguageValue);

        return ResponseEntity.ok(categoryLanguageResponseDtos);
    }

    @Override
    public ResponseEntity<ProductsResponseDto> addProduct(ProductRequestDto request) {
        Product product = productMapper.map(request);

        Long brandId = request.getBrandId();
        List<Long> categoriesId = request.getCategoriesId();

        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),NOT_FOUND.getMessage()), brandId));
        List<Category> categories = categoryRepository.findAllById(categoriesId);
        product.setBrand(brand);
        product.setCategories(categories);


        Map<Long, Map<Long, String>> requestAttributes = request.getAttributes();
        Set<Long> attributesId = requestAttributes.keySet();


        List<Attribute> attributes = attributeRepository.findAllById(attributesId);
        List<Language> languages = languageRepository.findAll();

        productRepository.save(product);

        for (Long attributeId : attributesId) {
            ProductAttribute productAttribute = ProductAttribute.builder()
                    .product(product)
                    .attribute(attributes.stream().filter(attribute ->
                                    attribute.getId().equals(attributeId))
                            .findFirst().orElseThrow())
                    .build();

            Map<Long, String> requestLanguage = requestAttributes.get(attributeId);

            for (Long languageId : requestLanguage.keySet()) {
                ProductAttributeLanguage productAttributeLanguage = ProductAttributeLanguage.builder()
                        .productAttribute(productAttribute)
                        .value(requestLanguage.get(languageId))
                        .language(languages.stream()
                                .filter(language ->
                                        language.getId().equals(languageId))
                                .findFirst()
                                .orElseThrow())
                        .build();
                productAttributeLanguageRepository.save(productAttributeLanguage);
            }
        }

        ProductsResponseDto productResponseDto = productHelperService.build(product);

        return ResponseEntity.ok(productResponseDto);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),NOT_FOUND.getMessage()), id));
    }

    private ResponseEntity<AttributeResponseDto> getAttributes(Long productId, Locale locale) {

        Product product = productRepository
                .findById(productId).orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),NOT_FOUND.getMessage()), productId));

        Map<String, String> attributeValue = new HashMap<>();

        try {

            product.getProductAttributes().forEach(productAttribute -> {

                        AttributeLang findedAttributeLang = productAttribute.getAttribute().getAttributeLangs().stream()
                                .filter(attributeLang -> attributeLang.getLanguage().getValue()
                                        .equals(locale.getLanguage().toUpperCase()))
                                .findAny().orElseThrow();

                        productAttribute.getProductAttributeLanguages().stream()
                                .filter(productAttributeLanguage -> productAttributeLanguage.getLanguage().getValue().equals(locale.getLanguage().toUpperCase()))
                                .forEach(productAttributeLanguage ->
                                        attributeValue.put(findedAttributeLang.getAttributeName(), productAttributeLanguage.getValue()));
                    }
            );
        } catch (RuntimeException ignored) {

        }
        AttributeResponseDto attributeResponseDto = AttributeResponseDto.builder().attributes(attributeValue).build();

        return ResponseEntity.ok(attributeResponseDto);
    }

    private boolean isAllSearch(FilterRequestDto dto) {
        return dto == null;
    }

}