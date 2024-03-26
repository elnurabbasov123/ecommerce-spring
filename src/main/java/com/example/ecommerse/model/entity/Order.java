package com.example.ecommerse.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private OrderStatus orderStatus;
    @ManyToOne
    private Customer customer;
    @OneToMany
    private List<CartItem> cartItems;
}
