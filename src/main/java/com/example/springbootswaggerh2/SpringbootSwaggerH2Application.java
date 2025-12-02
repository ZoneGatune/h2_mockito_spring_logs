package com.example.springbootswaggerh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringbootSwaggerH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSwaggerH2Application.class, args);
	}

}
