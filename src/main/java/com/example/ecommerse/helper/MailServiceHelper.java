package com.example.ecommerse.helper;

import com.example.ecommerse.model.dto.MailDto;
import com.example.ecommerse.model.entity.User;

public interface MailServiceHelper {
    MailDto buildMailDtoForConfirm(User user, String token);
    MailDto buildMailDtoForRenewPassword(User user,String otp);

    MailDto buildMailDtoForPaymentInfo(User user,Double price);
}
