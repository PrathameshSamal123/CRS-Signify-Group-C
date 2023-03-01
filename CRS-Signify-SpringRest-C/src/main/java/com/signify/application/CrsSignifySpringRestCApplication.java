package com.signify.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.signify.*")  // This annotation scans all the spring component like service, restcontroller, dao based on package patterns
@EnableWebMvc
public class CrsSignifySpringRestCApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsSignifySpringRestCApplication.class, args);
		System.out.println("Hello Spring");
	}

}
