package com.project.ecommerce.request;

import lombok.Data;

@Data
public class PromoCodeCreateRequest {

    private Long userId;
    private int amount;
    private int year;
    private int month;
    private int date;
}
