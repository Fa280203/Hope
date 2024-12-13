package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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
    public ResponseEntity<?> connexion(@RequestParam String login, @RequestParam String password) {
        Optional<Utilisateur> utilisateur = utilisateurService.connecterUtilisateur(login, password);

        if (utilisateur.isPresent()) {
            return ResponseEntity.ok(utilisateur.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou mot de passe incorrect.");
        }
    }

}
