package com.grids.circle.gccoffee.repository;

import com.grids.circle.gccoffee.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 이메일로 주문 내역 조회 (비회원용)
    List<Order> findByEmail(String email);
}