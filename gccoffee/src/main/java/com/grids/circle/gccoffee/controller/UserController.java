package com.grids.circle.gccoffee.controller;

import com.grids.circle.gccoffee.config.UserPrincipal;
import com.grids.circle.gccoffee.entity.User;
import com.grids.circle.gccoffee.service.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    // 로그인은 추후 세션/토큰 기반으로 확장 예정
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(Map.of(
            "username", user.getUsername(),
            "role", user.getAuthorities().iterator().next().getAuthority()
        ));
    }
}
