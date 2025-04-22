package com.grids.circle.gccoffee.controller;

import com.grids.circle.gccoffee.repository.MenuRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private final MenuRepository menuRepository;

    public ProductController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping("/product")
    public String showProductPage(Model model) {
        model.addAttribute("menus", menuRepository.findAll());
        return "product";
    }
}