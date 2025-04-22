package com.grids.circle.gccoffee.service;

import com.grids.circle.gccoffee.entity.User;
import com.grids.circle.gccoffee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }

    public User register(User user) {
        // 비밀번호 암호화나 중복 확인 등의 로직은 여기에 넣을 수 있음

        // 기본 role 설정
        if (user.getRole() == null) {
            user.setRole("USER");  // 또는 Role.USER.name() (enum이면)
        }

        return userRepository.save(user);
    }
}