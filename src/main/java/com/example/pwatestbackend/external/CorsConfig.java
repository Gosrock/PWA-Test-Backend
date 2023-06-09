package com.example.pwatestbackend.external;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("https://pwa-test-frontend.vercel.app/");
        allowedOriginPatterns.add("http://localhost:3000");
        allowedOriginPatterns.add("http://localhost:5173");
        allowedOriginPatterns.add("*");

        String[] patterns = allowedOriginPatterns.toArray(String[]::new);
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOriginPatterns(patterns)
            .exposedHeaders("Set-Cookie")
            .allowCredentials(true);
    }
}
