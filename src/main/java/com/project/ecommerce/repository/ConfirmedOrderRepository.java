package com.project.ecommerce.repository;

import com.project.ecommerce.model.ConfirmedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedOrderRepository extends JpaRepository<ConfirmedOrder, Long> {
    ConfirmedOrder findConfirmedOrderByOrderNumber(Long orderNumber);
}
