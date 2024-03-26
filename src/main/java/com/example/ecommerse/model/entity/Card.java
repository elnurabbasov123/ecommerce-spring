package com.example.ecommerse.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "cards")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String cvv;
    @Builder.Default
    private boolean status = true;
    private LocalDate expiredDate;
    @ManyToOne
    private User user;
}
