package com.example.ecommerse.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "addresses")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstAddress;
    private String secondAddress;
    private long zipCode;
    @ManyToOne
    private Country country;
}
