package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/pets/**") // Adjust the path if necessary
                        .allowedOrigins("http://localhost:63342") // Allow requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE"); // Allow these HTTP methods
            }
        };
    }
}