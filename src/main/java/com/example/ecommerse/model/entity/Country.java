package com.example.ecommerse.model.entity;

import com.example.ecommerse.model.enums.type.Countries;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Countries name;
    @Builder.Default
    private boolean status = true;
}
