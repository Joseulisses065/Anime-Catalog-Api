package br.com.animeapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSwaggerConfig {

    public OpenAPI getOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Anime API")
                        .version("v1")
                        .description("Anime api")
                        .contact(new Contact().name("José Ulisses").email("Contato.ulisses@Gmail.com"))
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT")));
    }
}
