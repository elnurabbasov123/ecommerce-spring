package com.example.ecommerse.model.entity;
import com.example.ecommerse.model.entity.Permission;
import com.example.ecommerse.model.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    private boolean enabled;
    @Builder.Default
    private boolean nonLocked = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Permission> permissions = role.getPermissions();
        permissions.add(new Permission(role.getName().name()));

        return permissions;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUsername() {
        return email;
    }

}