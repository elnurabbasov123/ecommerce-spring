package com.example.ecommerse.service.admin.impl;

import com.example.ecommerse.model.dto.request.AttributeRequestDto;
import com.example.ecommerse.model.dto.request.BrandRequestDto;
import com.example.ecommerse.model.dto.request.CategoryRequestDto;
import com.example.ecommerse.model.dto.request.LanguageRequestDto;
import com.example.ecommerse.model.entity.*;
import com.example.ecommerse.model.enums.type.NotificationType;
import com.example.ecommerse.model.enums.type.RoleType;
import com.example.ecommerse.repository.*;
import com.example.ecommerse.service.admin.AdminService;
import com.example.ecommerse.service.seller.SellerService;
import com.example.ecommerse.service.user.RoleService;
import com.example.ecommerse.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final UserService userService;
    private final AdminNotificationRepository adminNotificationRepository;
    private final SellerService sellerService;
    private final RoleService roleService;
    private final LanguageRepository languageRepository;
    private final AttributeLangRepository attributeLangRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final CategoryLanguageRepository categoryLanguageRepository;

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> confirmSeller(Long id) {
        Seller seller = sellerService.findByUser(id);
        Role sellerRole = roleService.getRoleByName(RoleType.ROLE_SELLER);
        seller.getUser().setRole(sellerRole);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addAttribute(AttributeRequestDto requestDto) {
        Map<Long, String> attributes = requestDto.getAttributes();
        Set<Long> languagesId = attributes.keySet();

        List<Language> languages = languageRepository.findAllById(languagesId);

        Attribute attribute = Attribute.builder()
                .build();

        for (Long languageId : languagesId) {

            AttributeLang attributeLang = AttributeLang.builder()
                    .language(languages.stream()
                            .filter(language -> language.getId().equals(languageId))
                            .findFirst()
                            .orElseThrow())
                    .attributeName(attributes.get(languageId))
                    .attribute(attribute)
                    .build();

            attributeLangRepository.save(attributeLang);
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addBrand(BrandRequestDto requestDto) {
        List<Long> categoriesId = requestDto.getCategoriesId();
        List<Category> categories = categoryRepository.findAllById(categoriesId);

        Brand brand = Brand.builder()
                .name(requestDto.getName())
                .build();

        brand.setCategories(categories);
        brandRepository.save(brand);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addLanguage(LanguageRequestDto requestDto) {
        Language language = Language.builder()
                .value(requestDto.getValue().toUpperCase())
                .build();
        languageRepository.save(language);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addCategory(CategoryRequestDto requestDto) {
        Map<Long, String> names = requestDto.getName();
        Map<Long, String> descriptions = requestDto.getDescription();

        Set<Long> languagesId = names.keySet();
        List<Language> languages = languageRepository.findAllById(languagesId);

        Category category = Category.builder()
                .build();

        for (Long languageId:languagesId){
            CategoryLanguage categoryLanguage = CategoryLanguage.builder()
                    .category(category)
                    .name(names.get(languageId))
                    .description(descriptions.get(languageId))
                    .language(languages.stream()
                            .filter(language -> language.getId().equals(languageId))
                            .findFirst()
                            .orElseThrow()).build();
            categoryLanguageRepository.save(categoryLanguage);
        }
        return ResponseEntity.ok().build();
    }
}
