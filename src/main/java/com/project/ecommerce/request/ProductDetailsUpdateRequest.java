package com.project.ecommerce.request;

import lombok.Data;

@Data
public class ProductDetailsUpdateRequest {

    private Long productId;

    private String productDetails;
}
