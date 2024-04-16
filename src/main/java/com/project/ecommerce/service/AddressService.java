package com.project.ecommerce.service;

import com.project.ecommerce.model.Address;

import java.util.List;

public interface AddressService {
    Address add(Address address);
    List<Address> getAll();
    void deleteById(Long id);
    List<Address> getAddressByUserId(Long userId);
    Address getById(Long id);
}
