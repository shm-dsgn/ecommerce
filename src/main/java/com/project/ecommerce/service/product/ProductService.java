package com.project.ecommerce.service.product;

import com.project.ecommerce.dto.createDto.ProductCreateDto;
import com.project.ecommerce.dto.viewDto.ProductViewDto;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.ConfirmedOrder;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.request.ConfirmCartRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    ProductCreateDto add(ProductCreateDto productCreateDto);
    List<Product> getByproductName(String productName);
    List<Product> getByproductBrand(String productBrand);
    void deleteById(Long id);
    void updateByProductDetails(Long productId, String productDetails);
    List<Product> slice(Pageable pageable);
    List<ProductViewDto> getDto();
    Cart addToCart(Long id);
    List<Cart> getCart();
    void removeFromCart(Long id);
    ConfirmedOrder confirmCart(ConfirmCartRequest confirmCartRequest);
    List<ConfirmedOrder> getAllConfirmedOrder();
    ConfirmedOrder getConfirmedOrderById(Long id);
    ConfirmedOrder getConfirmedOrderByOrderNumber(Long orderNumber);
    Map<Integer, Object> searchByProduct(String productName);
    void addFavorite(Long productId);
    int getNumberOfFavorite(Long productId);
    void removeFromFavorites(Long productId);
}
