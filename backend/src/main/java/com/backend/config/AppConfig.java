package com.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configuration class to set up beans
@Configuration
public class AppConfig {

    // Define a ModelMapper bean to convert between DTOs and entities
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // Define a WebMvcConfigurer bean to set up global CORS configuration to give
    // access to the frontend
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all endpoints
                        .allowedOrigins("http://localhost:3000", "http://localhost:4200") // Allow frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Specify allowed methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // To handle cookies or authorization headers
            }
        };
    }
}
