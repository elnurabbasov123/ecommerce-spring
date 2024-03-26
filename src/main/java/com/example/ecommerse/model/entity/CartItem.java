package com.example.ecommerse.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private BigDecimal totalPrice;
    @Builder.Default
    private boolean status = true;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;
}
