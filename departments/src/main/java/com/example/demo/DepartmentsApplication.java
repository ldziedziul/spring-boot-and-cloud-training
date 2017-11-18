package com.example.demo;

import com.example.demo.common.Swagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class DepartmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentsApplication.class, args);
	}
}
