package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query("select s from sellers s where s.user.id=:id")
    Optional<Seller> findByUser(@Param("id") Long id);
}