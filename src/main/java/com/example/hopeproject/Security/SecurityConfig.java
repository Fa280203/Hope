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
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll() // Pages publiques
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN") // Accessible uniquement aux ADMIN
                        .requestMatchers("/api/enseignant/**").hasAuthority("ENSEIGNANT") // Accessible aux ENSEIGNANTS
                        .requestMatchers("/api/etudiant/**").hasAuthority("ETUDIANT") // Accessible aux ETUDIANTS
                        .anyRequest().authenticated() // Tout le reste nécessite une connexion
                )
                .formLogin(form -> form
                        .loginPage("/login") // Page de connexion personnalisée
                        .defaultSuccessUrl("/home", true) // Redirection après connexion réussie
                        .failureUrl("/login?error=true") // Redirection en cas d'erreur
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL pour la déconnexion
                        .logoutSuccessUrl("/login?logout=true") // Redirection après déconnexion
                        .invalidateHttpSession(true) // Supprime la session
                        .deleteCookies("JSESSIONID") // Supprime le cookie de session
                        .permitAll()
                );

        return http.build();
    }
}
