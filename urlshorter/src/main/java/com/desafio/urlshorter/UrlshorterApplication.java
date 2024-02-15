package com.desafio.urlshorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.desafio.urlshorter.repositories")
public class UrlshorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlshorterApplication.class, args);
	}

}
