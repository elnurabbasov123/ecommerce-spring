package com.example.ecommerse.repository;


import com.example.ecommerse.model.entity.Role;
import com.example.ecommerse.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    @Modifying
    @Query("update users  u set u.role =:role where u =:user")
    void updateUser(@Param("user") User user, @Param("role") Role role);

}
