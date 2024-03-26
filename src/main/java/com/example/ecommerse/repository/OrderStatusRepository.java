package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.OrderStatus;
import com.example.ecommerse.model.enums.type.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findByOrderType(OrderType  orderType);
}