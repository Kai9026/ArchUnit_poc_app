package com.github.kai9026.archunit.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.github.kai9026.archunit.poc.repository")
public class ArchunitGettingStartedApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchunitGettingStartedApplication.class, args);
	}

}
