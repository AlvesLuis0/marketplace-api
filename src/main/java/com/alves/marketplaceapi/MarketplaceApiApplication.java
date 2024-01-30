package com.alves.marketplaceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MarketplaceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApiApplication.class, args);
	}

}