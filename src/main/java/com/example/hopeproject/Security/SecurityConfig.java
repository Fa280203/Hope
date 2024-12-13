package com.example.hopeproject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Nouvelle méthode pour désactiver CSRF

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/utilisateurs/**").permitAll() // Accessible à tous
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN") // Accessible uniquement aux ADMIN
                        .requestMatchers("/api/enseignant/**").hasAuthority("ENSEIGNANT") // Accessible uniquement aux ENSEIGNANTS
                        .requestMatchers("/api/etudiant/**").hasAuthority("ETUDIANT") // Accessible uniquement aux ÉTUDIANTS
                        .anyRequest().authenticated() // Toutes les autres routes nécessitent une authentification
                );

        return http.build();
    }
}
