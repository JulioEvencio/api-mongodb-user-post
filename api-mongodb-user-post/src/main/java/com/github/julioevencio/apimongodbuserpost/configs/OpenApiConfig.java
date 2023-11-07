package com.github.julioevencio.apimongodbuserpost.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		Info info = new Info()
				.title("API MongoDB User Post")
				.version("1.0")
				.description("API for Users and posts ")
				.termsOfService("https://github.com/JulioEvencio/api-mongodb-user-post/blob/main/LICENSE")
				.license(new License().name("MIT License").url("https://github.com/JulioEvencio/api-mongodb-user-post/blob/main/LICENSE"));
		
		return new OpenAPI().info(info);
	}
	
}
