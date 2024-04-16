package com.project.ecommerce.controller;

import com.project.ecommerce.model.Address;
import com.project.ecommerce.service.AddressService;
import com.project.ecommerce.shared.ECommerceMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address/")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Address address) {
        addressService.add(address);
        return ResponseEntity.ok(ECommerceMessage.ADDRESS_CREATED);
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.ok(ECommerceMessage.ADDRESS_DELETED);
    }

    @GetMapping("getAddressByUserId/{userId}")
    public ResponseEntity<?> getAddressByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(addressService.getAddressByUserId(userId));
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getById(id));
    }
}
