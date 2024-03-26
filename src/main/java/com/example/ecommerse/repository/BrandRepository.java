package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Transactional
    @Override
    <S extends Brand> S save(S entity);
}