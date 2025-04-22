package com.grids.circle.gccoffee.controller;

import com.grids.circle.gccoffee.entity.Order;
import com.grids.circle.gccoffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderSuccessController {

    private final OrderService orderService;

    @GetMapping("/order/success")
    public String showOrderSuccess(@RequestParam("orderId") Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        model.addAttribute("order", order);
        return "order-success";
    }
}
