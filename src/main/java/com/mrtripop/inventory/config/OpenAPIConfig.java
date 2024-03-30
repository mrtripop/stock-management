package com.mrtripop.inventory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  @Value("${config.author.name}")
  private String authorName;

  @Value("${config.author.email}")
  private String authorEmail;

  @Value("${config.app.version}")
  private String version;

  @Value("${config.app.local-url}")
  private String localUrl;

  @Value("${config.app.dev-url}")
  private String devUrl;

  @Value("${config.app.stg-url}")
  private String stgUrl;

  @Bean
  public OpenAPI openAPI() {
    Server localServer = new Server();
    localServer.setUrl(localUrl);
    localServer.setDescription("Localhost URL");

    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Development URL");

    Server stgServer = new Server();
    stgServer.setUrl(stgUrl);
    stgServer.setDescription("Staging URL");

    Contact contact = new Contact();
    contact.setName(authorName);
    contact.setEmail(authorEmail);

    License mitLicense =
        new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info =
        new Info()
            .title("Stock Management API")
            .version(version)
            .contact(contact)
            .description("This API exposes endpoints to manage stock.")
            .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(localServer, devServer, stgServer));
  }
}
