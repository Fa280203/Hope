package com.example.hopeproject.Service;

import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        // Retourne un utilisateur Spring Security avec ses rôles
        return org.springframework.security.core.userdetails.User.builder()
                .username(utilisateur.getLogin())
                .password(utilisateur.getMotDePasse()) // Mot de passe encodé
                .authorities(utilisateur.getRole().toString()) // Rôle comme autorité
                .build();
    }
}
