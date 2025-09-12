package com.example.jwt.jwt_auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())              // 403'ün en yaygın nedeni (CSRF)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()              // her şeyi geçici olarak aç
                );
        return http.build();
    }
}

