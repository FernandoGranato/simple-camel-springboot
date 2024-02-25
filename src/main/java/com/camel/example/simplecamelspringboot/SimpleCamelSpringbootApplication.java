package com.camel.example.simplecamelspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.camel.example.simplecamelspringboot.beans")
public class SimpleCamelSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCamelSpringbootApplication.class, args);
	}

}
