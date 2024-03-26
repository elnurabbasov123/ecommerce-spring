package com.example.ecommerse.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "files")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    @ManyToOne
    private Product product;
}
