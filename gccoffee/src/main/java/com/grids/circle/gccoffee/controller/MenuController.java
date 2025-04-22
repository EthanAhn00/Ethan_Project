package com.grids.circle.gccoffee.controller;

import com.grids.circle.gccoffee.entity.Menu;
import com.grids.circle.gccoffee.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public String showMenuPage(Model model) {
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "menus/list";  // Thymeleaf 템플릿 경로
    }

    @GetMapping("/{id}")
    public String showMenuDetail(@PathVariable Long id, Model model) {
        Menu menu = menuService.getMenuById(id)
            .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다. id=" + id));
        model.addAttribute("menu", menu);
        return "menus/detail"; // menus/detail.html
    }

    @GetMapping("/product")
    public String productPage() {
        return "product"; // → templates/product.html
    }
}