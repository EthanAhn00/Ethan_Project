package com.grids.circle.gccoffee.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartItemDto {
    private Long menuId;
    private String menuName;
    private int price;
    private int quantity;
}