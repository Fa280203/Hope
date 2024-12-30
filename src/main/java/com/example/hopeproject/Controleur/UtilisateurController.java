package com.example.hopeproject.Controleur;

import com.example.hopeproject.Exceptions.ConnexionException;
import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/utilisateurs")
@Tag(name = "UtilisateurController", description = "Gestion des utilisateurs")
public class UtilisateurController {
    private static final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private UtilisateurService utilisateurService;

    @Operation(summary = "Récupérer tous les utilisateurs", description = "Renvoie la liste de tous les utilisateurs sous forme JSON.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs récupérée avec succès"),
            @ApiResponse(responseCode = "204", description = "Aucun utilisateur trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Utilisateur>> recupererTousLesUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.recupererTousLesUtilisateurs();
        if (utilisateurs.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        }
        return ResponseEntity.ok(utilisateurs); // HTTP 200 OK
    }

    @Operation(summary = "Ajouter un nouvel utilisateur", description = "Ajoute un utilisateur à partir d'un objet JSON.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Utilisateur ajouté avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Utilisateur> ajouterUtilisateur(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Les informations de l'utilisateur à ajouter")
            @RequestBody Utilisateur utilisateur) {
        Utilisateur savedUtilisateur = utilisateurService.ajouterUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUtilisateur); // HTTP 201 Created
    }

    @Operation(summary = "Afficher la page de connexion", description = "Renvoie la page HTML de connexion des utilisateurs.")
    @ApiResponse(responseCode = "200", description = "Page de connexion affichée avec succès")
    @GetMapping("/connexion")
    public String afficherPageConnexion() {
        return "connexion"; // Renvoie connexion.html dans le dossier templates
    }

    @Operation(summary = "Déconnexion de l'utilisateur", description = "Invalide la session de l'utilisateur et redirige vers la page de connexion.")
    @ApiResponse(responseCode = "302", description = "Utilisateur déconnecté avec succès")
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("Déconnexion de l'utilisateur. ID de session : {}", session.getId());
        session.invalidate(); // Invalide la session
        return "redirect:/utilisateurs/connexion"; // Redirection vers la page de connexion
    }

    @Operation(summary = "Vérifier les informations de connexion", description = "Gère le formulaire de connexion et authentifie l'utilisateur.")
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Connexion réussie, redirection vers la page d'accueil"),
            @ApiResponse(responseCode = "401", description = "Nom d'utilisateur ou mot de passe incorrect"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping("/connexion")
    public String verifierConnexion(
            @Parameter(description = "Nom d'utilisateur pour la connexion") @RequestParam String username,
            @Parameter(description = "Mot de passe de l'utilisateur") @RequestParam String password,
            HttpServletRequest request,
            Model model
    ) {
        try {
            // Invalide la session existante
            request.getSession().invalidate();

            // Appelle le service pour authentifier l'utilisateur
            Utilisateur utilisateur = utilisateurService.connecterUtilisateur(username, password);

            // Crée une nouvelle session et stocke les informations utilisateur
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("nom", utilisateur.getNom());
            newSession.setAttribute("prenom", utilisateur.getPrenom());
            newSession.setAttribute("role", utilisateur.getRole().name());
            newSession.setAttribute("login", utilisateur.getLogin());
            newSession.setAttribute("id", utilisateur.getId());

            // Redirection vers une autre page en cas de succès
            return "redirect:/outils";
        } catch (ConnexionException ex) {
            // Ajoute un message d'erreur dans le modèle et retourne à la page de connexion
            model.addAttribute("errorMessage", ex.getMessage());
            return "connexion";
        }
    }
}
