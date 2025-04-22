package com.grids.circle.gccoffee.repository;

import com.grids.circle.gccoffee.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}