package com.grids.circle.gccoffee.service;

import com.grids.circle.gccoffee.dto.OrderRequestDto;
import com.grids.circle.gccoffee.entity.*;
import com.grids.circle.gccoffee.repository.MenuRepository;
import com.grids.circle.gccoffee.repository.OrderRepository;
import com.grids.circle.gccoffee.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    @Transactional
    public void placeOrder(OrderRequestDto dto) {
        List<OrderItem> orderItems = new ArrayList<>();

        dto.getItems().forEach(itemDto -> {
            Menu menu = menuRepository.findById(itemDto.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("Menu not found: " + itemDto.getMenuId()));

            OrderItem orderItem = OrderItem.builder()
                .menu(menu)
                .price(itemDto.getPrice())
                .quantity(itemDto.getQuantity())
                .menuName(itemDto.getMenuName())
                .build();

            orderItems.add(orderItem);
        });

        Order.OrderBuilder orderBuilder = Order.builder()
            .orderItems(orderItems)
            .orderDate(LocalDateTime.now())
            .status(OrderStatus.CREATED);

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + dto.getUserId()));
            orderBuilder.user(user);
        } else {
            orderBuilder.email(dto.getEmail())
                .address(dto.getAddress())
                .postCode(dto.getPostCode());
        }

        orderRepository.save(orderBuilder.build());
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}