package com.bridgelabz.addressbookapp.config;

/**
 * Global Web MVC configuration.
 * Enables CORS for the React frontend dev server (http://localhost:3000)
 * so that cross-origin API calls from the browser are permitted.
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Allow all addressbookservice endpoints to be called from the React dev server
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/addressbookservice/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
