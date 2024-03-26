package com.example.ecommerse.model.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity(name = "category_languages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CategoryLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(unique = true,nullable = false)
    private String name;
    @ManyToOne
    private Language language;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
