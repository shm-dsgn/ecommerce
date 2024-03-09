package com.project.ecommerce.controller;

import com.project.ecommerce.model.Seller;
import com.project.ecommerce.request.SellerCreateRequest;
import com.project.ecommerce.service.seller.SellerService;
import com.project.ecommerce.shared.ECommerceMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/seller/")
@RequiredArgsConstructor
@CrossOrigin
public class SellerController {
    private final SellerService sellerService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sellerService.getAll());
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody SellerCreateRequest seller) {
        sellerService.add(new Seller(seller.getName(), seller.getProfilePictureUrl(), seller.getEMail(), new Date()));
        return ResponseEntity.ok(ECommerceMessage.SELLER_SAVED);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        sellerService.deleteById(id);
        return ResponseEntity.ok(ECommerceMessage.SELLER_DELETED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sellerService.getById(id));
    }

}
