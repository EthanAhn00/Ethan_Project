package com.grids.circle.gccoffee.service;

import com.grids.circle.gccoffee.entity.Menu;

import com.grids.circle.gccoffee.dto.MenuDto;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface MenuService {
    Menu createMenu(Menu menu);
    List<Menu> getAllMenus();
    Optional<Menu> getMenuById(Long id);
    Menu updateMenu(Long id, Menu updatedMenu);
    void deleteMenu(Long id);
    Menu createMenu(MenuDto menuDto, MultipartFile image);
}