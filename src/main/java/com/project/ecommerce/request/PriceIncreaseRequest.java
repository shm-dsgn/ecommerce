package com.project.ecommerce.request;

import lombok.Data;

@Data
public class PriceIncreaseRequest {

    private Long productId;

    private int amount;
}
