package com.example.ecommerse.service.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Void> delete(long id);

    void deleteById(Long id);
}
