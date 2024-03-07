package com.project.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "CART")
public class Cart {

    @Id
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productBrand")
    private String productBrand;

    @Column(name = "productDetails")
    private String productDetails;

    @Column(name = "productPrice")
    private double productPrice;

    @Column(name = "productImageUrl")
    private String productImageUrl;

    @ManyToOne
    private Seller seller;
}

