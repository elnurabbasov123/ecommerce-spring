package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Cart;
import com.example.ecommerse.model.entity.CartItem;
import com.example.ecommerse.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProductAndCartAndStatus(Product product, Cart cart, boolean status);

    List<CartItem> findAllByCartAndStatus(Cart cart,boolean status);
}