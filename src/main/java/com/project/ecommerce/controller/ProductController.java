package com.project.ecommerce.controller;

import com.project.ecommerce.dto.createDto.ProductCreateDto;
import com.project.ecommerce.dto.viewDto.ProductViewDto;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.request.CampaignCreateRequest;
import com.project.ecommerce.request.ConfirmCartRequest;
import com.project.ecommerce.request.PriceIncreaseRequest;
import com.project.ecommerce.request.ProductDetailsUpdateRequest;
import com.project.ecommerce.service.product.ProductService;
import com.project.ecommerce.service.product.UpdateProductPriceService;
import com.project.ecommerce.shared.ECommerceMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    private final UpdateProductPriceService updateProductPriceService;


    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid ProductCreateDto productCreateDto) {
        this.productService.add(productCreateDto);
        return ResponseEntity.ok(ECommerceMessage.PRODUCT_SAVED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        final List<Product> products = productService.getAll();
        if(products.size() <= 0) {
            return ResponseEntity.ok(ECommerceMessage.PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("getByProductName/{productName}")
    public ResponseEntity<?> getByproductName(@PathVariable String productName) {
        String productsName = "";
        List<Product> products = this.productService.getByproductName(productName);
        for(Product product: products) {
            productsName = product.getProductName();
        }
        if(!productName.equals(productsName)) {
            return ResponseEntity.ok(ECommerceMessage.NOT_FOUND_THIS_NAME);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("getByProductBrand/{productBrand}")
    public List<Product> getByproductBrand(@PathVariable String productBrand) {
        return this.productService.getByproductBrand(productBrand);
    }

    @GetMapping("slice")
    public ResponseEntity<List<Product>> slice(Pageable pageable) {
        final List<Product> products = this.productService.slice(pageable);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable Long id) {
        this.productService.deleteById(id);
        return ResponseEntity.ok(ECommerceMessage.PRODUCT_DELETED);
    }

    @GetMapping("getDto")
    public ResponseEntity<?> getDto() {
        List<ProductViewDto> products = this.productService.getDto();
        if(products.size() <= 0) {
            return ResponseEntity.ok(ECommerceMessage.PRODUCT_NOT_FOUND);
        }
        else {
            return ResponseEntity.ok(products);
        }
    }

    @PostMapping("addToCart/{id}")
    public ResponseEntity<?> addToCart(@RequestBody @PathVariable Long id) {
        productService.addToCart(id);
        return ResponseEntity.ok(ECommerceMessage.ADD_TO_CART);
    }

    @GetMapping("getCart")
    public ResponseEntity<?> getCart() {
        return ResponseEntity.ok(productService.getCart());
    }

    @DeleteMapping("removeFromCart/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long id) {
        productService.removeFromCart(id);
        return ResponseEntity.ok(ECommerceMessage.REMOVE_FROM_CART);
    }

    @GetMapping("searchByProduct/{productName}")
    public ResponseEntity<?> searchByProduct(@PathVariable String productName) {
        Map<Integer, Object> result = productService.searchByProduct(productName);
        return ResponseEntity.ok(result);
    }

    @PostMapping("confirmCart")
    public ResponseEntity<?> confirmCart(@RequestBody ConfirmCartRequest confirmCartRequest) {
        productService.confirmCart(confirmCartRequest);
        return ResponseEntity.ok(ECommerceMessage.ITEMS_IN_THE_CART_HAVE_BEEN_PURCHASED);
    }

    @GetMapping("getAllConfirmedCart")
    public ResponseEntity<?> getAllConfirmedOrder() {
        return ResponseEntity.ok(productService.getAllConfirmedOrder());
    }

    @GetMapping("getConfirmedOrderById/{id}")
    public ResponseEntity<?> getConfirmedOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getConfirmedOrderById(id));
    }

    @GetMapping("getConfirmedOrderByOrderNumber/{orderNumber}")
    public ResponseEntity<?> getConfirmedOrderByOrderNumber(@PathVariable Long orderNumber) {
        return ResponseEntity.ok(productService.getConfirmedOrderByOrderNumber(orderNumber));
    }

    @PutMapping("createCampaign")
    public ResponseEntity<?> createCampaign(@RequestBody CampaignCreateRequest campaignCreateRequest) {
        updateProductPriceService.createCampaign(campaignCreateRequest);
        return ResponseEntity.ok("success");
    }

    @PutMapping("priceIncrease")
    public ResponseEntity<?> priceIncrease(@RequestBody PriceIncreaseRequest priceIncreaseRequest) {
        updateProductPriceService.priceIncrease(priceIncreaseRequest);
        return ResponseEntity.ok("success");
    }

    @PutMapping("update-product-details")
    public ResponseEntity<?> updateByProductDetails(@RequestBody ProductDetailsUpdateRequest updateRequest) {
        productService.updateByProductDetails(updateRequest.getProductId(), updateRequest.getProductDetails());
        return ResponseEntity.ok("success");
    }

    @PutMapping("addFavorite/{productId}")
    public ResponseEntity<?> addFavorite(@PathVariable("productId") Long productId) {
        productService.addFavorite(productId);
        return ResponseEntity.ok(ECommerceMessage.ADDED_TO_FAVORITES);
    }

    @GetMapping("getNumberOfFavorite/{productId}")
    public ResponseEntity<?> getNumberOfFavorite(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.getNumberOfFavorite(productId));
    }

    @PutMapping("removeFromFavorite/{productId}")
    public ResponseEntity<?> removeFromFavorite(@PathVariable("productId") Long productId) {
        productService.removeFromFavorites(productId);
        return ResponseEntity.ok(ECommerceMessage.REMOVE_FROM_FAVORITES);
    }
}
