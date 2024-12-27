package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalide la session pour supprimer les attributs utilisateur
        return "redirect:/utilisateurs/connexion"; // Redirection vers la page de connexion
    }
    // Gérer le formulaire de connexion
    @PostMapping("/connexion")
    public String verifierConnexion(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request
    ) {
        // Invalide la session existante
        request.getSession().invalidate();

        // Crée une nouvelle session
        HttpSession newSession = request.getSession(true);

        // Trouve l'utilisateur avec le login et le mot de passe
        var utilisateur = utilisateurService.connecterUtilisateur(username, password);

        if (utilisateur.isPresent()) {
            // Ajoute les informations de l'utilisateur à la nouvelle session
            newSession.setAttribute("nom", utilisateur.get().getNom());
            newSession.setAttribute("prenom", utilisateur.get().getPrenom());
            newSession.setAttribute("role", utilisateur.get().getRole().name());
            newSession.setAttribute("login", utilisateur.get().getLogin());
            newSession.setAttribute("id",utilisateur.get().getId());// Ajoute le rôle dans la session

            return "redirect:/outils";
        }

        // Si l'utilisateur n'est pas trouvé, retourne à la page de connexion avec une erreur
        return "connexion";
    }


}
