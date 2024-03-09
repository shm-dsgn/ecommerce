package com.project.ecommerce.service.seller;

import com.project.ecommerce.model.Seller;

import java.util.List;

public interface SellerService {
    Seller add(Seller seller);
    void deleteById(Long id);
    List<Seller> getAll();
    Seller getById(Long id);
}
