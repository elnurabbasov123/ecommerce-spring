package com.example.ecommerse.service.authentication.impl;

import com.example.ecommerse.config.JwtService;
import com.example.ecommerse.model.dto.request.LoginRequestDto;
import com.example.ecommerse.model.dto.request.UserRequestDto;
import com.example.ecommerse.model.dto.response.MessageResponseDto;
import com.example.ecommerse.model.dto.response.TokenResponseDto;
import com.example.ecommerse.model.entity.*;
import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.messages.Exceptions;
import com.example.ecommerse.model.enums.messages.Message;
import com.example.ecommerse.model.enums.type.OtpName;
import com.example.ecommerse.model.enums.type.RoleType;
import com.example.ecommerse.model.exception.NotFoundException;
import com.example.ecommerse.model.exception.response.ExceptionResponse;
import com.example.ecommerse.model.mapper.UserMapper;
import com.example.ecommerse.repository.*;
import com.example.ecommerse.service.authentication.AuthenticationService;
import com.example.ecommerse.service.authentication.OtpService;
import com.example.ecommerse.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ecommerse.model.enums.messages.Exceptions.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserMapper userMapper;

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final OtpRepository otpRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleService roleService;

    @Override
    public ResponseEntity<MessageResponseDto> register(UserRequestDto userRequestDto) {

        User user = userMapper.map(userRequestDto);
        Role role = roleService.getRoleByName(RoleType.ROLE_USER);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setRole(role);

        Cart cart = Cart.builder()
                .user(user).build();

        userRepository.save(user);
        cartRepository.save(cart);

        otpService.sendOtp(user);

        return ResponseEntity.ok().body(MessageResponseDto.builder()
                .message(Message.OTP_SENT_MESSAGE.getMessage()).build());
    }

    @Override
    @Transactional
    public ResponseEntity<MessageResponseDto> confirmation(String otpValue, String email) {

        Otp otp = otpRepository.getOtp(otpValue, email, OtpName.CONFIRMATION).orElseThrow(() ->
                new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),
                        NOT_FOUND.getMessage()), otpValue));

        User user = otp.getUser();

        otp.setConfirm(false);
        user.setEnabled(true);

        return ResponseEntity.ok(MessageResponseDto.builder().message(Message.CONFIRM_USER_MESSAGE.getMessage()).build());
    }

    @Override
    @SneakyThrows
    public ResponseEntity<TokenResponseDto> login(LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));


        String jwtToken = jwtService.generateToken(request.getEmail(), getAuthorities(authentication));

        return ResponseEntity.ok(TokenResponseDto.builder().accessToken(jwtToken).build());
    }


    @Override
    public User getAuthenticatedUser() {
        return userRepository
                .findByEmail(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString())
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }

    private Map<String, Object> getAuthorities(Authentication authentication) {
        Map<String, Object> extraClaims = new HashMap<>();
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        extraClaims.put("authorities", authorities);
        return extraClaims;

    }
}