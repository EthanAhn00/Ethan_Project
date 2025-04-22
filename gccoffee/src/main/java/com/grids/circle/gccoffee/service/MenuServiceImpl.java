package com.grids.circle.gccoffee.service;

import com.grids.circle.gccoffee.entity.Menu;
import com.grids.circle.gccoffee.dto.MenuDto;
import com.grids.circle.gccoffee.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final FileStorageService fileStorageService;

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    @Override
    public Menu updateMenu(Long id, Menu updatedMenu) {
        return menuRepository.findById(id)
            .map(menu -> {
                menu.setName(updatedMenu.getName());
                menu.setDescription(updatedMenu.getDescription());
                menu.setPrice(updatedMenu.getPrice());
                return menuRepository.save(menu);
            })
            .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다. id: " + id));
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public Menu createMenu(MenuDto menuDto, MultipartFile image) {
        // 1. 이미지 저장
        String imageUrl = fileStorageService.store(image);  // 이미지 저장 서비스 필요

        // 2. Menu 엔티티로 변환
        Menu menu = Menu.builder()
            .name(menuDto.getName())
            .description(menuDto.getDescription())
            .price(menuDto.getPrice())
            .imageUrl(imageUrl)
            .build();

        // 3. 저장
        return menuRepository.save(menu);
    }
}