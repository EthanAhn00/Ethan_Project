package com.grids.circle.gccoffee.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private List<CartItemDto> items;  // 장바구니 항목들
    private Long userId;              // 회원 주문일 경우
    private String email;             // 비회원일 경우
    private String address;
    private String postCode;

    @Getter @Setter
    public static class OrderItemDto {
        private Long menuId;
        private int quantity;
    }
}