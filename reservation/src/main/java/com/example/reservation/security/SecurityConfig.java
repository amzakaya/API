package com.example.reservation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/users/register", "/api/users/{username}", "/api/businesses", 
            		"/api/businesses/category/**","/api/appointments").permitAll() // İlgili endpoint'i açık hale getir
            .anyRequest().authenticated() // Diğer endpoint'ler kimlik doğrulama gerektirir
            .and()
            .httpBasic(); // Basic Auth kullanımı (isteğe bağlı)

        return http.build();
    }
}
