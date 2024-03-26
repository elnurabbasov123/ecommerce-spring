package com.example.ecommerse.service.authentication.impl;

import com.example.ecommerse.model.entity.Otp;
import com.example.ecommerse.model.entity.OtpType;
import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.type.OtpName;
import com.example.ecommerse.repository.OtpRepository;
import com.example.ecommerse.repository.OtpTypeRepository;
import com.example.ecommerse.service.mail.MailService;
import com.example.ecommerse.service.authentication.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpTypeRepository otpTypeRepository;
    private final MailService mailService;
    private final OtpRepository otpRepository;

    @Override
    public void sendOtp(User user) {
        OtpType type = otpTypeRepository.getByName(OtpName.CONFIRMATION)
                .orElseThrow();

        String otp = generateOtp();

        Otp otpBuilder = Otp.builder()
                .otpType(type)
                .otp(otp)
                .user(user)
                .expired(LocalDateTime.now().plusSeconds(180))
                .confirm(true)
                .build();

        otpRepository.save(otpBuilder);

        mailService.createAndSendToMailForConfirmation(user,otp);
    }

    private String generateOtp() {
        return  String.valueOf(new Random().nextInt(1000,9999));
    }
}
