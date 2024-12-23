package com.example.hopeproject.Service;


import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Utilisateur> recupererTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> recupererUtilisateurParLogin(String login) {
        return utilisateurRepository.findByLogin(login);
    }

    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        System.out.println("Utilisateur avant sauvegarde : " + utilisateur);

        // Encodage du mot de passe
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

        Utilisateur savedUser = utilisateurRepository.save(utilisateur);
        System.out.println("Utilisateur après sauvegarde : " + savedUser);

        return savedUser;
    }


    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Optional<Utilisateur> connecterUtilisateur(String login, String motDePasse) {
        return utilisateurRepository.findByLoginAndMotDePasse(login, motDePasse);
    }


}
