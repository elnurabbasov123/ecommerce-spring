package com.example.ecommerse.service.user;

import com.example.ecommerse.model.dto.request.CustomerRequestDto;
import com.example.ecommerse.model.entity.Customer;
import com.example.ecommerse.model.entity.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<Void> register(CustomerRequestDto requestDto);

    Customer findByCustomerByUser(User user);
}
