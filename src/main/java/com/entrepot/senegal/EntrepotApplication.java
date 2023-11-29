package com.entrepot.senegal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.entrepot.senegal.service.UserService;

@SpringBootApplication
public class EntrepotApplication {

	@Autowired
	private UserService us;

	public static void main(String[] args) {

		SpringApplication.run(EntrepotApplication.class, args);
	
	}

}
