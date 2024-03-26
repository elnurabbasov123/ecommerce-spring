package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Cart;
import com.example.ecommerse.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}