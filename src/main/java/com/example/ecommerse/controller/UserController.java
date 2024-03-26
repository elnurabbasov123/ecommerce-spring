package com.example.ecommerse.controller;

import com.example.ecommerse.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(required = false) Long id){
        return userService.delete(id);
    }
}
