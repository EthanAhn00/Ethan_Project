package com.grids.circle.gccoffee.controller;

import com.grids.circle.gccoffee.dto.OrderRequestDto;
import com.grids.circle.gccoffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // 비회원 주문 (이메일, 주소, 우편번호, 장바구니만 필요)
    @PostMapping("/guest")
    public ResponseEntity<Void> placeGuestOrder(@RequestBody OrderRequestDto requestDto) {
        orderService.placeOrder(requestDto); // 이메일, address, postCode만 세팅된 상태
        return ResponseEntity.ok().build();
    }

    // 회원 주문 (userId만 있으면 됨)
    @PostMapping("/member")
    public ResponseEntity<Void> placeMemberOrder(@RequestBody OrderRequestDto requestDto) {
        orderService.placeOrder(requestDto); // userId가 세팅된 상태
        return ResponseEntity.ok().build();
    }
}