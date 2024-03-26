package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByValue(String lang);
}