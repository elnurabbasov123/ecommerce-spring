package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Customer;
import com.example.ecommerse.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from customers c where c.user=:user")
    Optional<Customer> findByUser(@Param("user") User user);
}