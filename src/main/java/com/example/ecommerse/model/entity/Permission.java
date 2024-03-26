package com.example.ecommerse.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
@Getter
@Entity(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "permissions")
    @ToString.Exclude
    private Set<Role> roles;

    public Permission(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
