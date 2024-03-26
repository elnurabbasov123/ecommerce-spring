package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Attribute;
import com.example.ecommerse.model.entity.AttributeLang;
import com.example.ecommerse.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AttributeLangRepository extends JpaRepository<AttributeLang, Long> {
    @Query("select a from attribute_languages a " +
            "where a.attribute =:attribute and a.language.value =:lang")
    Optional<AttributeLang> findById(
            @Param("attribute") Attribute attribute,
            @Param("lang") String lang
            );
}