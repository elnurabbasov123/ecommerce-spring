package com.example.ecommerse.service.seller.impl;

import com.example.ecommerse.model.dto.request.SellerRequestDto;
import com.example.ecommerse.model.entity.Customer;
import com.example.ecommerse.model.entity.Seller;
import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.messages.Exceptions;
import com.example.ecommerse.model.enums.type.NotificationType;
import com.example.ecommerse.model.exception.NotFoundException;
import com.example.ecommerse.model.exception.response.ExceptionResponse;
import com.example.ecommerse.model.mapper.SellerMapper;
import com.example.ecommerse.repository.SellerRepository;
import com.example.ecommerse.service.admin.AdminService;
import com.example.ecommerse.service.authentication.AuthenticationService;
import com.example.ecommerse.service.common.NotificationService;
import com.example.ecommerse.service.seller.SellerService;
import com.example.ecommerse.service.user.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.example.ecommerse.model.enums.messages.Exceptions.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerMapper sellerMapper;
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;
    private final SellerRepository sellerRepository;
    private final NotificationService notificationService;

    @Override
    public ResponseEntity<Void> registration(SellerRequestDto requestDto) {
        User user = authenticationService.getAuthenticatedUser();
        Customer customer = customerService.findByCustomerByUser(user);


        Seller seller = sellerMapper.map(requestDto);
        seller.setUser(user);
        seller.setName(customer.getName());
        seller.setSurname(customer.getSurname());

        sellerRepository.save(seller);

        notificationService.saveNotification(user, NotificationType.CONFIRM_SELLER);

        return ResponseEntity.ok().build();
    }

    @Override
    public Seller findByUser(Long id) {
        return sellerRepository.findByUser(id)
                .orElseThrow(()->new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),NOT_FOUND.getMessage()),id));
    }
}
