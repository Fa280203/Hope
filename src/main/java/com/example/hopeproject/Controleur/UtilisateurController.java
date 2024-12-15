package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.hopeproject.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> recupererTousLesUtilisateurs() {
        return utilisateurService.recupererTousLesUtilisateurs();
    }

    @PostMapping
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.ajouterUtilisateur(utilisateur);
    }

    @DeleteMapping("/{id}")
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }

    @GetMapping("/connexion")
    public String afficherPageConnexion() {
        return "connexion"; // Renvoie le fichier connexion.html dans /templates
    }

    // Gère la soumission du formulaire de connexion
    @PostMapping("/connexion")
    public String verifierConnexion(@RequestParam String username, @RequestParam String password, Model model) {
        var utilisateur = utilisateurService.connecterUtilisateur(username, password);

        if (utilisateur.isPresent()) {
            // Connexion réussie : redirige vers outils.html
            return "redirect:/outils";
        } else {
            // Connexion échouée : reste sur la page de connexion avec un message d'erreur
            model.addAttribute("erreur", "Identifiant ou mot de passe incorrect.");
            return "connexion";
        }
    }


}

