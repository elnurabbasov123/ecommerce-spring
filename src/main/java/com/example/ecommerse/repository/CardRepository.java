package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Card;
import com.example.ecommerse.model.entity.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByUserAndStatus(User user, boolean status);


    Optional<Card> findByIdAndUserAndStatus(Long id, User user,boolean status);
}