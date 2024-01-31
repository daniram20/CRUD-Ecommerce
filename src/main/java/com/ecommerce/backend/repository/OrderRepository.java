package com.ecommerce.backend.repository;

import com.ecommerce.backend.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
