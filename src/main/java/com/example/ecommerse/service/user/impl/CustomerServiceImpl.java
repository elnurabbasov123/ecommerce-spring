package com.example.ecommerse.service.user.impl;

import com.example.ecommerse.model.dto.request.CustomerRequestDto;
import com.example.ecommerse.model.entity.*;
import com.example.ecommerse.model.enums.messages.Exceptions;
import com.example.ecommerse.model.enums.type.RoleType;
import com.example.ecommerse.model.exception.NotFoundException;
import com.example.ecommerse.model.exception.response.ExceptionResponse;
import com.example.ecommerse.model.mapper.AddressMapper;
import com.example.ecommerse.model.mapper.CustomerMapper;
import com.example.ecommerse.repository.CountryRepository;
import com.example.ecommerse.repository.CustomerRepository;
import com.example.ecommerse.repository.UserRepository;
import com.example.ecommerse.service.authentication.AuthenticationService;
import com.example.ecommerse.service.user.CustomerService;
import com.example.ecommerse.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.ecommerse.model.enums.messages.Exceptions.NOT_FOUND;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CountryRepository countryRepository;
    private final AuthenticationService authenticationService;
    private final AddressMapper addressMapper;
    private final CustomerRepository customerRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ResponseEntity<Void> register(CustomerRequestDto requestDto) {
        User user = authenticationService.getAuthenticatedUser();

        Address address = addressMapper.map(requestDto.getAddressRequestDto());
        Role customerRole = roleService.getRoleByName(RoleType.ROLE_CUSTOMER);

        Customer customer = customerMapper.map(requestDto);

        user.setRole(customerRole);
        customer.setUser(user);


        long countryId = requestDto.getAddressRequestDto().getCountryId();

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),NOT_FOUND.getMessage()), countryId));

        address.setCountry(country);
        customer.setAddress(address);

        customerRepository.save(customer);
        userRepository.save(user);


        return ResponseEntity.ok().build();
    }

    @Override
    public Customer findByCustomerByUser(User user) {
        return customerRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),NOT_FOUND.getMessage()), user.getEmail()));
    }
}
