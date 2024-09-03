package com.example.lab10_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab10SpringApplication {

	public static void main(String[] args) {

		SpringApplication.run(
				Lab10SpringApplication.class, args);
	}

}
//Napisz aplikację, która uruchomi serwer webowy, który po
// połączeniu wyświetli napis “Hello World”.