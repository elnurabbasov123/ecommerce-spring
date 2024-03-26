package com.example.ecommerse.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "attribute_languages")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AttributeLang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String attributeName;
    @ManyToOne
    private Language language;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Attribute attribute;
}
