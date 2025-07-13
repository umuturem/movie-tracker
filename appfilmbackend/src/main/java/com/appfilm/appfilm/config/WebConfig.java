package com.appfilm.appfilm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Tüm endpoint'lere izin ver
                        .allowedOrigins("http://localhost:5173") // React portuna izin ver
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Kullanılacak yöntemler
                        .allowedHeaders("*");
            }
        };
    }
}
