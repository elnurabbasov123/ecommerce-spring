package com.example.ecommerse.model.entity;
import com.example.ecommerse.model.enums.type.OrderType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name = "order_status")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
}
