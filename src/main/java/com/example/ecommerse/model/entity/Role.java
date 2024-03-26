package com.example.ecommerse.model.entity;

import com.example.ecommerse.model.enums.type.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Entity(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleType name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions;

}
