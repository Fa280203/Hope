package com.example.hopeproject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                        .requestMatchers("/api/utilisateurs/connexion", "/css/**", "/js/**").permitAll() // Autorise connexion et ressources statiques
                        .requestMatchers("/api/**").hasAuthority("ADMIN") // Admin a accès à toutes les APIs
                        .anyRequest().authenticated() // Toute autre requête nécessite une authentification
                )
                .formLogin(form -> form
                        .loginPage("/api/utilisateurs/connexion") // Page de connexion personnalisée
                        .defaultSuccessUrl("/outils", true) // Redirection après connexion réussie
                        .failureUrl("/api/utilisateurs/connexion?error=true") // Redirection en cas d'échec
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL pour la déconnexion
                        .logoutSuccessUrl("/api/utilisateurs/connexion?logout=true") // Redirection après déconnexion
                        .permitAll()
                );

        return http.build();
    }
}
