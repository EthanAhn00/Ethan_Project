package com.grids.circle.gccoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // → templates/index.html로 렌더링
    }

    @GetMapping("/index")
    public String index() {
        return "index"; // 혹시 몰라서 이 경로도 추가
    }
}