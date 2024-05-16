package com.mrtripop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
