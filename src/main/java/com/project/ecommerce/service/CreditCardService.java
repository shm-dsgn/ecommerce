package com.project.ecommerce.service;

import com.project.ecommerce.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    List<CreditCard> getAll();
    CreditCard add(CreditCard creditCard);
    List<CreditCard> getCreditCardByUserId(Long id);
    CreditCard getBydId(Long id);
}
