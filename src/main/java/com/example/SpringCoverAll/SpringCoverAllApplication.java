package com.example.SpringCoverAll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:jwt_secret.properties")
public class SpringCoverAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoverAllApplication.class, args);
	}

}
