package com.grids.circle.gccoffee.repository;

import com.grids.circle.gccoffee.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @RequiredArgsConstructor
    @Service
    public class OrderService {
        private final MenuRepository menuRepository;
        // ...
    }

}
