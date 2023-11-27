package com.example.jeeSpring;

import com.example.jeeSpring.Entities.SiteUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class JeeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeeSpringApplication.class, args);
	}

}