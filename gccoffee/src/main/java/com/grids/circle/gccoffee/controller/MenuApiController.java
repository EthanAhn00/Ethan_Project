package com.grids.circle.gccoffee.controller;

import com.grids.circle.gccoffee.dto.MenuDto;
import com.grids.circle.gccoffee.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuApiController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Void> createMenu(
        @RequestPart("menu") MenuDto menuDto,
        @RequestPart("image") MultipartFile imageFile) {

        menuService.createMenu(menuDto, imageFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<MenuDto> getMenus() {
        return menuService.getAllMenus().stream()
            .map(menu -> MenuDto.builder()
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .imageUrl(menu.getImageUrl())
                .build())
            .toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }
}