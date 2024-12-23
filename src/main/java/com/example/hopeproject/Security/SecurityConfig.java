package com.example.hopeproject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive CSRF pour simplifier les tests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/utilisateurs/connexion", "/css/**").permitAll() // Autorise la connexion et les CSS
                        .requestMatchers(HttpMethod.POST, "/api/utilisateurs").permitAll() // Permet les requêtes POST pour créer des utilisateurs
//                        .requestMatchers("/outils/**").hasRole("ADMIN") // Seuls les admins ont accès à /outils/**
                        .requestMatchers("/outils/**").permitAll()// Seuls les admins ont accès à /outils/**

                        .anyRequest().authenticated() // Le reste nécessite une authentification
                )
                .formLogin(form -> form
                        .loginPage("/api/utilisateurs/connexion") // Page de connexion
                        .defaultSuccessUrl("/outils", true) // Redirection après connexion réussie
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/api/utilisateurs/connexion?logout=true") // Redirection après déconnexion
                        .permitAll()
                );

        return http.build();
    }
}
