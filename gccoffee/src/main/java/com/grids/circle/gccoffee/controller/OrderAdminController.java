package com.grids.circle.gccoffee.controller;

import com.grids.circle.gccoffee.repository.OrderRepository;
import com.grids.circle.gccoffee.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderRepository orderRepository;

    @GetMapping("/admin/orders")
    public String showAllOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "admin-order-list";
    }
}