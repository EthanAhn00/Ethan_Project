package com.grids.circle.gccoffee.controller;

import com.grids.circle.gccoffee.cart.CartItem;
import com.grids.circle.gccoffee.entity.Menu;
import com.grids.circle.gccoffee.service.MenuService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final MenuService menuService;

    // 장바구니 조회
    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(HttpSession session) {
        return ResponseEntity.ok(getSessionCart(session));
    }

    // 장바구니에 추가
    @PostMapping("/{menuId}")
    public ResponseEntity<Void> addToCart(
        @PathVariable Long menuId,
        @RequestParam(defaultValue = "1") int quantity,
        HttpSession session
    ) {
        List<CartItem> cart = getSessionCart(session);
        Menu menu = menuService.getMenuById(menuId).orElseThrow();

        Optional<CartItem> existing = cart.stream()
            .filter(item -> item.getMenuId().equals(menuId))
            .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + quantity);
        } else {
            cart.add(CartItem.builder()
                .menuId(menu.getId())
                .menuName(menu.getName())
                .price(menu.getPrice())
                .quantity(quantity)
                .build());
        }

        session.setAttribute("cart", cart);
        return ResponseEntity.ok().build();
    }

    // 장바구니에서 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> removeFromCart(
        @PathVariable Long menuId,
        HttpSession session
    ) {
        List<CartItem> cart = getSessionCart(session);
        cart.removeIf(item -> item.getMenuId().equals(menuId));
        session.setAttribute("cart", cart);
        return ResponseEntity.ok().build();
    }

    // private 메소드: 세션에서 cart 꺼내거나 새로 만듦
    private List<CartItem> getSessionCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}