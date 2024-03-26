package com.example.ecommerse.service.mail;


import com.example.ecommerse.model.dto.MailDto;
import com.example.ecommerse.model.entity.User;

public interface MailService {
    void sendmail(MailDto mailDto);

    void createAndSendToMailForConfirmation(User user, String otp);
    void createAndSendToMailForRefresh(User user,String token);

    void createAndSendToMailForPaymentInfo(User user,Double payment);
}
