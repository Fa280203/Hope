package com.example.hopeproject.Controleur;

import com.example.hopeproject.Exceptions.InvalidOutilException;
import com.example.hopeproject.Exceptions.OutilIntrouvableException;
import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Service.FeedbackService;
import com.example.hopeproject.Service.OutilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/outils")
@Tag(name = "OutilController", description = "Gestion des outils")
public class OutilController {

    private static final Logger logger = LoggerFactory.getLogger(OutilController.class);

    @Autowired
    private OutilService outilService;

    @Autowired
    private FeedbackService feedbackService;

    // Affiche la page des outils validés
    @Operation(summary = "Afficher la page des outils", description = "Affiche tous les outils validés avec des informations de session.")
    @GetMapping
    public String afficherPageOutils(Model model, HttpSession session) {
        String nom = (String) session.getAttribute("nom");
        String prenom = (String) session.getAttribute("prenom");
        List<String> domaines = outilService.recupererDomaines();

        model.addAttribute("nom", nom);
        model.addAttribute("prenom", prenom);
        model.addAttribute("outils", outilService.recupererOutilsValides());
        model.addAttribute("domaines", domaines);

        logger.info("Page des outils affichée pour {} {}", nom, prenom);
        return "outils";
    }

    // API REST pour récupérer tous les outils
    @Operation(summary = "Récupérer tous les outils (API)", description = "Retourne tous les outils sous forme de JSON.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Outils récupérés avec succès"),
            @ApiResponse(responseCode = "204", description = "Aucun outil disponible")
    })
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Outil>> recupererTousLesOutils() {
        List<Outil> outils = outilService.recupererTousLesOutils();
        if (outils.isEmpty()) {
            logger.warn("Aucun outil disponible.");
            return ResponseEntity.noContent().build();
        }
        logger.info("Outils récupérés avec succès.");
        return ResponseEntity.ok(outils);
    }

    // API REST pour récupérer un outil par ID
    @Operation(summary = "Récupérer un outil par ID (API)", description = "Retourne les détails d'un outil spécifique via son ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails de l'outil récupérés avec succès"),
            @ApiResponse(responseCode = "404", description = "Outil non trouvé")
    })
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Outil> recupererOutilParId(@PathVariable Long id) {
        return outilService.recupererOutilParId(id)
                .map(outil -> {
                    logger.info("Outil trouvé avec ID : {}", id);
                    return ResponseEntity.ok(outil);
                })
                .orElseThrow(() -> new OutilIntrouvableException("Outil introuvable."));
    }

    // API REST pour ajouter un outil
    // Afficher le formulaire d'ajout d'un outil
    @Operation(summary = "Afficher le formulaire d'ajout d'un outil", description = "Affiche un formulaire vide pour ajouter un nouvel outil.")
    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("outil", new Outil());
        return "ajouter"; // Correspond au fichier templates/ajouter.html
    }

    // Soumettre un nouvel outil via formulaire HTML
    @Operation(summary = "Soumettre un nouvel outil", description = "Ajoute un nouvel outil selon le rôle de l'utilisateur.")
    @PostMapping("/ajouter")
    public String ajouterOutil(
            @RequestParam String titre,
            @RequestParam String domaine,
            @RequestParam String descriptionSimple,
            @RequestParam String lien,
            @RequestParam String acces,
            HttpSession session
    ) {
        String role = (String) session.getAttribute("role");

        Outil nouvelOutil = new Outil();
        nouvelOutil.setTitre(titre);
        nouvelOutil.setDomaine(domaine);
        nouvelOutil.setDescriptionSimple(descriptionSimple);
        nouvelOutil.setLien(lien);
        nouvelOutil.setAcces(acces);

        if ("ADMIN".equals(role)) {
            nouvelOutil.setBooleanWithDefaultValue(true); // Ajout direct pour les admins
        } else {
            nouvelOutil.setBooleanWithDefaultValue(false); // En attente de validation pour les autres
        }

        outilService.ajouterOutil(nouvelOutil);
        logger.info("Nouvel outil ajouté par {} : {}", role, titre);
        return "redirect:/outils"; // Retour à la liste des outils
    }


    // API REST pour supprimer un outil
    @Operation(summary = "Supprimer un outil (API)", description = "Supprime un outil ainsi que tous ses feedbacks associés.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Outil supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Outil non trouvé")
    })
    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> supprimerOutil(@PathVariable Long id) {
        if (!outilService.recupererOutilParId(id).isPresent()) {
            throw new OutilIntrouvableException("Outil avec l'ID " + id + " introuvable.");
        }
        feedbackService.supprimerFeedbacksParOutilId(id);
        outilService.supprimerOutil(id);
        logger.info("Outil avec ID {} supprimé avec succès.", id);
        return ResponseEntity.ok().build();
    }

    // Supprimer un outil (redirection HTML)
    @PostMapping("/supprimer/{id}")
    public String supprimerOutilHtml(@PathVariable Long id) {
        feedbackService.supprimerFeedbacksParOutilId(id);
        outilService.supprimerOutil(id);
        logger.info("Outil supprimé avec ID {}", id);
        return "redirect:/outils";
    }

    // Afficher les détails d'un outil
    @Operation(summary = "Afficher les détails d'un outil", description = "Affiche les détails d'un outil spécifique ainsi que ses feedbacks associés.")
    @GetMapping("/details/{uuid}")
    public String afficherDetailsOutil(@PathVariable String uuid, Model model) {
        Outil outil = outilService.recupererOutilParUuid(uuid)
                .orElseThrow(() -> new OutilIntrouvableException("Outil introuvable."));
        List<Feedback> feedbacks = feedbackService.recupererFeedbacksParOutil(outil.getId());

        model.addAttribute("titre", outil.getTitre());
        model.addAttribute("descriptionDetaillee", outil.getDescriptionDetaillee());
        model.addAttribute("acces", outil.getAcces());
        model.addAttribute("descriptionSimple", outil.getDescriptionSimple());
        model.addAttribute("domaine", outil.getDomaine());
        model.addAttribute("lien", outil.getLien());
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("outilUuid", outil.getUuid());
        model.addAttribute("outilId", outil.getId());

        return "details";
    }

    // Afficher le formulaire de modification
    @GetMapping("/modifier/{id}")
    public String afficherPageModifierOutil(@PathVariable Long id, Model model) {
        var outil = outilService.recupererOutilParId(id)
                .orElseThrow(() -> new IllegalArgumentException("Outil introuvable"));

        model.addAttribute("outil", outil);
        return "modifier";
    }

    // Modifier un outil
    @PostMapping("/modifier")
    public String modifierOutil(
            @RequestParam Long id,
            @RequestParam String titre,
            @RequestParam String domaine,
            @RequestParam String descriptionSimple,
            @RequestParam String lien,
            @RequestParam String acces
    ) {
        var outil = outilService.recupererOutilParId(id)
                .orElseThrow(() -> new IllegalArgumentException("Outil introuvable"));

        outil.setTitre(titre);
        outil.setDomaine(domaine);
        outil.setDescriptionSimple(descriptionSimple);
        outil.setLien(lien);
        outil.setAcces(acces);

        outilService.ajouterOutil(outil);
        logger.info("Outil avec ID {} modifié avec succès.", id);
        return "redirect:/outils";
    }

    // Afficher les outils non validés
    @GetMapping("/admin/validation")
    public String afficherOutilsNonValides(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            return "redirect:/outils";
        }

        List<Outil> outilsNonValides = outilService.recupererOutilsNonValides();
        model.addAttribute("outilsNonValides", outilsNonValides);

        return "adminValidation";
    }

    // Valider un outil
    @PostMapping("/valider/{id}")
    public String validerOutil(@PathVariable Long id) {
        var outil = outilService.recupererOutilParId(id)
                .orElseThrow(() -> new IllegalArgumentException("Outil introuvable"));

        outil.setBooleanWithDefaultValue(true);
        outilService.ajouterOutil(outil);
        logger.info("Outil avec ID {} validé avec succès.", id);

        return "redirect:/outils";
    }

    // Rechercher des outils
    @GetMapping("/recherche")
    public String rechercherOutils(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String domaine,
            Model model,
            HttpSession session
    ) {
        String nom = (String) session.getAttribute("nom");
        String prenom = (String) session.getAttribute("prenom");

        model.addAttribute("nom", nom);
        model.addAttribute("prenom", prenom);

        // Recherche des outils validés uniquement
        List<Outil> resultats = outilService.rechercherOutils(query, domaine);
        model.addAttribute("outils", resultats);

        // Récupération des domaines pour les outils validés uniquement
        List<String> domaines = outilService.recupererDomaines();
        model.addAttribute("domaines", domaines);

        return "outils";
    }

}
