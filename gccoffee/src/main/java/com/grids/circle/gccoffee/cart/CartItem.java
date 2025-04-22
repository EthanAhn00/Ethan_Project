package com.grids.circle.gccoffee.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long menuId;
    private String menuName;
    private int price;
    private int quantity;
}