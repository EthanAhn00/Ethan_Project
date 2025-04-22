package com.grids.circle.gccoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GccoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GccoffeeApplication.class, args);
	}

	@Bean
	public CommandLineRunner testRunner() {
		return args -> {
			System.out.println("✅ Spring Boot 프로젝트 시작 완료!");
		};
	}

}

