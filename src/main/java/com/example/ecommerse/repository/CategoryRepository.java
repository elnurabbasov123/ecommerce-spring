package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    @Transactional
    Optional<Category> findById(Long aLong);
}