package com.project.ecommerce.repository;

import com.project.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getByproductName(String productName);
    List<Product> getByproductBrand(String productBrand);
    void deleteById(Long id);
}