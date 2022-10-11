package com.sun.collectiblestore;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CollectibleStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectibleStoreApplication.class, args);
	}

	@Bean
	public OpenAPI openAPI(){
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo(){
		return new Info().title("Collectible Store").description("Java store example").version("0.0.1");
	}

}
