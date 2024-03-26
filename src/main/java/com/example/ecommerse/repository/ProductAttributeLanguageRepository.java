package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.ProductAttribute;
import com.example.ecommerse.model.entity.ProductAttributeLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductAttributeLanguageRepository extends JpaRepository<ProductAttributeLanguage, Long> {
//    @Override
    @Query("select pal from product_attribute_languages pal" +
            " where pal.productAttribute =:prAttr and pal.language.value =:lang")
    Optional<ProductAttributeLanguage> findById(@Param("lang") String language, @Param("prAttr") ProductAttribute productAttribute);
}