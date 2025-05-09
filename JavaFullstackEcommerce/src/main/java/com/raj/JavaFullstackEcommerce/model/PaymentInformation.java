package com.raj.JavaFullstackEcommerce.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation {

    @Column(name = "cardholder_name")
    private String  cardHolderName;

    @Column(name = "card_Number")
    private String cardNumber;

    @Column(name = "expire_date")
    private LocalDate  expirationDate;

    @Column(name = "cvv")
    private String cvv;
}
