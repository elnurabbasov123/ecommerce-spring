package com.example.ecommerse;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ECommerceApplication{
    public static void main(String[] args) {
         SpringApplication.run(ECommerceApplication.class, args);
    }

}
