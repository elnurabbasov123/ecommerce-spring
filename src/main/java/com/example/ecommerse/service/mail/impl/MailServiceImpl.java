package com.example.ecommerse.service.mail.impl;

import com.example.ecommerse.helper.MailServiceHelper;
import com.example.ecommerse.model.dto.MailDto;
import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final MailServiceHelper mailServiceHelper;

    @Override
    @SneakyThrows
    public void sendmail(MailDto mailDto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setSubject(mailDto.getSubject());
        mimeMessageHelper.setTo(mailDto.getTo());
        mimeMessageHelper.setText(mailDto.getText(),true);

        javaMailSender.send(message);
    }

    @Override
    public void createAndSendToMailForConfirmation(User user, String otp) {
        MailDto mailDto = mailServiceHelper.buildMailDtoForConfirm(user, otp);
        sendmail(mailDto);
    }

    @Override
    public void createAndSendToMailForRefresh(User user, String token) {
        MailDto mailDto = mailServiceHelper.buildMailDtoForRenewPassword(user, token);
        sendmail(mailDto);
    }

    @Override
    public void createAndSendToMailForPaymentInfo(User user,Double price) {
        MailDto mailDto = mailServiceHelper.buildMailDtoForPaymentInfo(user, price);
        sendmail(mailDto);
    }
}
