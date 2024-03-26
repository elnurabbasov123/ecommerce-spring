package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Role;
import com.example.ecommerse.model.enums.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> getRoleByName(RoleType roleType);
}
