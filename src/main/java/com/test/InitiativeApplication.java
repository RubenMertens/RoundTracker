package com.test;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InitiativeApplication {

	public static final Gson GSON = new Gson();

	public static void main(String[] args) {
		SpringApplication.run(InitiativeApplication.class, args);
	}
}
