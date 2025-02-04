package com.example.hopeproject.Controleur;

import com.example.hopeproject.Exceptions.FeedbackQuotaExceededException;
import com.example.hopeproject.Exceptions.OutilIntrouvableException;
import com.example.hopeproject.Exceptions.UtilisateurNonTrouveException;
import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Service.FeedbackService;
import com.example.hopeproject.Service.OutilService;
import com.example.hopeproject.Service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/feedbacks")
@Tag(name = "FeedbackController", description = "Gestion des feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private OutilService outilService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Operation(summary = "Afficher le formulaire de feedback", description = "Affiche un formulaire pour ajouter un feedback pour un outil donné.")
    @GetMapping("/formulaire/{outilUuid}")
    public String afficherFormulaireFeedback(@PathVariable String outilUuid, Model model) {
        outilService.recupererOutilParUuid(outilUuid)
                .orElseThrow(() -> new OutilIntrouvableException("Outil introuvable."));

        model.addAttribute("outilUuid", outilUuid);
        return "feedback";
    }


    @Operation(summary = "Ajouter un feedback", description = "Ajoute un feedback pour un outil donné.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Feedback ajouté avec succès"),
            @ApiResponse(responseCode = "400", description = "Utilisateur non connecté ou quota de feedback dépassé"),
            @ApiResponse(responseCode = "404", description = "Outil introuvable")
    })
    @PostMapping("/ajouter")
    public String ajouterFeedback(
            @RequestParam String contenu,
            @RequestParam String outilUuid,
            HttpSession session
    ) {
        String login = (String) session.getAttribute("login");
        Long utilisateurId = (Long) session.getAttribute("id");
        String role = (String) session.getAttribute("role");

        if (login == null || utilisateurId == null) {
            throw new UtilisateurNonTrouveException("Vous devez être connecté pour ajouter un feedback.");
        }

        Outil outil = outilService.recupererOutilParUuid(outilUuid)
                .orElseThrow(() -> new OutilIntrouvableException("Outil introuvable."));

        Long outilId = outil.getId();

        List<Feedback> userFeedbacksForOutil = feedbackService.recupererFeedbacksParUtilisateurEtOutil(utilisateurId, outilId);

        int maxFeedbacks = switch (role) {
            case "ADMIN" -> 5;
            case "ETUDIANT" -> 2;
            case "ENSEIGNANT" -> 3;
            default -> 1;
        };

        if (userFeedbacksForOutil.size() >= maxFeedbacks) {
            throw new FeedbackQuotaExceededException("Vous avez atteint le quota maximum de " + maxFeedbacks + " feedbacks pour cet outil.");
        }

        Utilisateur utilisateur = utilisateurService.recupererUtilisateurParLogin(login);
        Feedback feedback = new Feedback();
        feedback.setContenu(contenu);
        feedback.setOutil(outil);
        feedback.setUtilisateur(utilisateur);

        feedbackService.ajouterFeedback(feedback);

        return "redirect:/outils";
    }


    @Operation(summary = "Supprimer un feedback", description = "Supprime un feedback via son ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Feedback introuvable")
    })
    @DeleteMapping("/supprimer/{id}")
    @ResponseBody
    public ResponseEntity<String> supprimerFeedback(@PathVariable Long id) {
        if (!feedbackService.existeFeedback(id)) {
            throw new IllegalArgumentException("Feedback introuvable.");
        }
        feedbackService.supprimerFeedback(id);
        return ResponseEntity.ok("Feedback supprimé avec succès.");
    }
}
