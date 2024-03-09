package com.project.ecommerce.request;

import lombok.Data;

@Data
public class ConfirmCartRequest {

    private Long id;

    private String cardNumber;

    private int cvv;

    private String expirationDate;

    private String nameAndSurname;

    private String promoCode;
}