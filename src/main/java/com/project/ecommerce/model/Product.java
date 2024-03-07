package com.project.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "product_details")
    private String productDetails;

    @Min(value = 1, message = "{com.eCommerceProject.Min.price.message}")
    @Column(name = "product_price")
    private double productPrice;

    @Min(value = 1, message = "{com.eCommerceProject.Min.stock.message}")
    @Column(name = "stock")
    private int stock;

    @Column(name = "image")
    private String productImageUrl;

    @Column(name = "favoriteNumber")
    @Min(value = 0)
    private int favoriteNumber = 0;

    @ManyToOne
    private Seller seller;

    @OneToMany
    private List<ProductComment> productComment;

    @ManyToOne
    private Category category;


    public Product(String productName, String productBrand, String productDetails, double productPrice, int stock, String productImageUrl) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDetails = productDetails;
        this.productPrice = productPrice;
        this.stock = stock;
        this.productImageUrl = productImageUrl;
    }
}
