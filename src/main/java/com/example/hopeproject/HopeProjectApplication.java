package com.example.hopeproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "HopeProject API", version = "1.0", description = "Documentation de l'API HopeProject")
)
public class HopeProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(HopeProjectApplication.class, args);
    }
}
