package com.example.ecommerse.service.authentication;

import com.example.ecommerse.model.entity.User;

public interface OtpService {
    void sendOtp(User user);
}
