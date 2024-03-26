package com.example.ecommerse.helper.impl;

import com.example.ecommerse.helper.MailServiceHelper;
import com.example.ecommerse.model.dto.MailDto;
import com.example.ecommerse.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.example.ecommerse.model.constant.EndPoints.*;


import jakarta.servlet.http.HttpServletRequest;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailServiceHelperImpl implements MailServiceHelper {
    @Value(value = "${server.custom.protocol}")
    private String PROTOCOL;
    @Value(value = "${server.custom.address}")
    private String SERVER_ADDRESS;
    @Value(value = "${server.custom.port}")
    private String SERVER_PORT;
    @Value("${server.custom.methode.upload.file.uri}")
    private String URI;

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    @Override
    public MailDto buildMailDtoForConfirm(User user, String otp) {

        String url = PROTOCOL + SERVER_ADDRESS + SERVER_PORT + AUTH_CONFIRM + getParams(user, otp);

        return MailDto.builder()
                .to(user.getEmail())
                .subject("Registration")
                .text("<p> Hi, " + user.getUsername() + ", <p>" +
                        "<p>Thank you for registering with us," +
                        "Please, follow the link below to complete your registration.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Users Registration Portal Service")
                .build();
    }

    private String getParams(User user, String otp) {
        return "?otp=" + otp + "&username=" + user.getEmail();
    }

    @Override
    public MailDto buildMailDtoForRenewPassword(User user, String otp) {
        String url = "http://" + "localhost" + ":" + getRequest().getServerPort() + "/users/reset-password?token=" + otp;


        return MailDto.builder()
                .to(user.getEmail())
                .subject("Reset Password")
                .text("<p> Hi, " + user.getUsername() + ", <p>" +
                        "<p>Thank you for change password with us," +
                        "Please, follow the link below to complete your change password.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Users change password Portal Service")
                .build();
    }

    @Override
    public MailDto buildMailDtoForPaymentInfo(User user, Double price) {

        return MailDto.builder()
                .to(user.getEmail())
                .subject("Payment Process")
                .text("Sizin Kartinizdan " + price + "azn cixildi")
                .build();
    }
}
