package com.example.ecommerse.service.user.impl;

import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.type.RoleType;
import com.example.ecommerse.model.exception.NotFoundException;
import com.example.ecommerse.model.exception.response.ExceptionResponse;
import com.example.ecommerse.repository.UserRepository;
import com.example.ecommerse.service.authentication.AuthenticationService;
import com.example.ecommerse.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.ecommerse.model.enums.messages.Exceptions.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<Void> delete(long id) {
        User user = authenticationService.getAuthenticatedUser();
        User deletedUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()), NOT_FOUND.getMessage()), id));
        if (user.getRole().getName().equals(RoleType.ROLE_ADMIN)) {
            deletedUser.setEnabled(false);
            return ResponseEntity.ok().build();
        }
            user.setEnabled(false);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()), NOT_FOUND.getMessage()), id));
        user.setEnabled(false);
    }

}
