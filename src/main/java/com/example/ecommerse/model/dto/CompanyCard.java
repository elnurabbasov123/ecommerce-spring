package com.example.ecommerse.model.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class CompanyCard {
    @Value(value = "${card.info.name}")
    private String name;
    @Value(value = "${card.info.surname}")
    private String surname;
    @Value(value = "${card.info.cardNumber}")
    private String cardNumber;
    @Value(value = "${card.info.cvv}")
    private String cvv;
    @Value(value = "${card.info.date}")
    private String expireDate;
}
