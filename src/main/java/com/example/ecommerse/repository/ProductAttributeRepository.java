package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute,Long> {
    @Query("select pa from product_attributes pa join products p on pa.product = p " +
            " where p.id =:productId")
    List<ProductAttribute> findAll(@Param("productId") Long productId);
}
