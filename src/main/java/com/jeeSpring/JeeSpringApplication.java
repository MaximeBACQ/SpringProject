package com.jeeSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JeeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeeSpringApplication.class, args);
	}

}