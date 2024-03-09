package com.project.ecommerce.service.product;

import com.project.ecommerce.request.CampaignCreateRequest;
import com.project.ecommerce.request.PriceIncreaseRequest;

public interface UpdateProductPriceService {
    void createCampaign(CampaignCreateRequest campaignCreateRequest);
    void priceIncrease(PriceIncreaseRequest priceIncreaseRequest);
}
