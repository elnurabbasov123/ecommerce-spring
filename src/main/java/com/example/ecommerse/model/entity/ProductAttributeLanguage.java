package com.example.ecommerse.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity(name = "product_attribute_languages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttributeLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @ManyToOne
    private Language language;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ProductAttribute productAttribute;
}
