package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.File;
import com.example.ecommerse.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAllByProduct(Product product);
}