package com.example.ecommerse.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name = "admins")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User user;
}
