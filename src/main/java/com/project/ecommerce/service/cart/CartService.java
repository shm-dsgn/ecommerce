package com.project.ecommerce.service.cart;

import com.project.ecommerce.model.Cart;

import java.util.List;

public interface CartService {
    Cart getById(Long id);
    void deleteById(Long id);
    List<Cart> getAll();
    void add(Cart cart);
}