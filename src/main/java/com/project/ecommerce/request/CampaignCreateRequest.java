package com.project.ecommerce.request;

import lombok.Data;

@Data
public class CampaignCreateRequest {

    Long productId;

    int discountAmount;

}