package com.example.hopeproject.Service;

import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Repository.UtilisateurRepository;
import com.example.hopeproject.Exceptions.ConnexionException;
import com.example.hopeproject.Exceptions.UtilisateurNonTrouveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Utilisateur> recupererTousLesUtilisateurs() {
        logger.info("Récupération de tous les utilisateurs");
        return utilisateurRepository.findAll();
    }


    public Utilisateur recupererUtilisateurParLogin(String login) {
        logger.info("Recherche de l'utilisateur avec le login : {}", login);
        return utilisateurRepository.findByLogin(login)
                .orElseThrow(() -> {
                    logger.error("Utilisateur avec le login {} non trouvé.", login);
                    return new UtilisateurNonTrouveException("Utilisateur avec le login '" + login + "' non trouvé.");
                });
    }

    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        logger.info("Ajout d'un nouvel utilisateur : {}", utilisateur);
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        Utilisateur savedUser = utilisateurRepository.save(utilisateur);
        logger.info("Utilisateur ajouté avec succès : {}", savedUser);
        return savedUser;
    }

    public Utilisateur connecterUtilisateur(String login, String motDePasse) {
        logger.info("Tentative de connexion pour l'utilisateur : {}", login);
        Utilisateur utilisateur = utilisateurRepository.findByLogin(login)
                .orElseThrow(() -> {
                    logger.error("Échec de connexion pour l'utilisateur : {}", login);
                    return new ConnexionException("Nom d'utilisateur ou mot de passe incorrect.");
                });

        if (!passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
            logger.error("Mot de passe incorrect pour l'utilisateur : {}", login);
            throw new ConnexionException("Nom d'utilisateur ou mot de passe incorrect.");
        }

        logger.info("Connexion réussie pour l'utilisateur : {}", login);
        return utilisateur;
    }
}
