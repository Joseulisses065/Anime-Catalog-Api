package br.com.animeapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "AnimeApi", version = "1.0",description = "Anime API with Spring Boot"))
public class AnimeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeapiApplication.class, args);
	}

}
