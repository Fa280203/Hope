package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Liste tous les utilisateurs (format JSON)
    @GetMapping("/api")
    @ResponseBody
    public List<Utilisateur> recupererTousLesUtilisateurs() {
        return utilisateurService.recupererTousLesUtilisateurs();
    }

    // Ajouter un utilisateur (format JSON)
    @PostMapping("/api")
    @ResponseBody
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.ajouterUtilisateur(utilisateur);
    }

    // Afficher la page de connexion
    @GetMapping("/connexion")
    public String afficherPageConnexion() {
        return "connexion"; // Renvoie connexion.html dans le dossier templates
    }

    // Gérer le formulaire de connexion
    @PostMapping("/connexion")
    public String verifierConnexion(@RequestParam String username, @RequestParam String password, Model model) {
        var utilisateur = utilisateurService.connecterUtilisateur(username, password);

        if (utilisateur.isPresent()) {
            // Si l'utilisateur est trouvé, redirige vers la page des outils
            return "redirect:/outils";
        } else {
            // Sinon, affiche un message d'erreur
            model.addAttribute("erreur", "Identifiant ou mot de passe incorrect.");
            return "connexion"; // Reste sur la page de connexion
        }
    }
}
