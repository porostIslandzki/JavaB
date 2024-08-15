package com.maria.maria2;

import com.maria.maria2.repository.CarProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class Maria2Application {
	public static void main(String[] args) {
		SpringApplication.run(Maria2Application.class, args);
	}
/*
	@Bean
	@ConfigurationProperties(prefix = "car")
	CarProperties carProperties(){
		return new CarProperties();
	}
	*/
	@Value("${my.dogs}")
	private List<String> dogs;
	@Value("${show.dogs}")
	private boolean showDogs;
	@PostConstruct
	void showDogs(){
		if(showDogs){
			dogs.forEach(System.out::println);
		}else{
			System.out.println("brak");
		}
	}
}
