package com.example.ecommerse.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity(name = "product_attributes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;
    @ManyToOne
    private Attribute attribute;
    @OneToMany(mappedBy = "productAttribute",fetch = FetchType.EAGER)
    private List<ProductAttributeLanguage> productAttributeLanguages;
}
