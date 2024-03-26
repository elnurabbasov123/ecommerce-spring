package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}