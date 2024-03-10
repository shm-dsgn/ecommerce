package com.project.ecommerce.service;

import com.project.ecommerce.model.PromoCode;
import com.project.ecommerce.request.PromoCodeCreateRequest;

import java.util.List;

public interface PromoCodeService {
    String createPromoCode(PromoCodeCreateRequest promoCodeRequest);
    List<PromoCode> getAll();
    PromoCode getById(Long id);
    void deleteById(Long id);
}
