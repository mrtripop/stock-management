package com.mrtripop.inventory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
  private String devUrl = "http://localhost:8080";

  private String prodUrl = "https://mrtripop.com";

  @Bean
  public OpenAPI openAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Development URL");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Production URL");

    Contact contact = new Contact();
    contact.setEmail("tripop.chai12@gmail.com");
    contact.setName("Tripop Torcheep");

    License mitLicense =
        new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info =
        new Info()
            .title("Stock Management API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage stock.")
            .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}
