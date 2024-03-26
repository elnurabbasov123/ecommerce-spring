package com.example.ecommerse.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private BigDecimal price;
    @ManyToOne
    private Brand brand;
    @ManyToMany
    private List<Category> categories;
    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<ProductAttribute> productAttributes;
}
