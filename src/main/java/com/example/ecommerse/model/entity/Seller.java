package com.example.ecommerse.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "sellers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String serialNumber;
    private String fin;
    private String livingAddress;
    @OneToOne()
    private User user;
}
