package com.project.ecommerce.service.seller;

import com.project.ecommerce.model.SellerComment;
import java.util.List;

public interface SellerCommentService {
    SellerComment add(SellerComment sellerComment);
    void deleteById(Long id);
    List<SellerComment> getAll();
    SellerComment getById(Long id);
    List<SellerComment> getSellerCommentsBySeller(Long id);
}
