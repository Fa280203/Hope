package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Modele.Utilisateur;
import com.example.hopeproject.Service.FeedbackService;
import com.example.hopeproject.Service.OutilService;
import com.example.hopeproject.Service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private OutilService outilService;  // Ajout de l'annotation @Autowired ici

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/formulaire/{outilId}")
    public String afficherFormulaireFeedback(@PathVariable Long outilId, Model model) {
        model.addAttribute("outilId", outilId);
        return "feedback"; // Correspond au fichier templates/feedbackForm.html
    }

    @PostMapping("/ajouter")
    public String ajouterFeedback(
            @RequestParam String contenu,
            @RequestParam Long outilId,
            HttpSession session,
            Model model
    ) {
        String login = (String) session.getAttribute("login");
        Long utilisateurId = (Long) session.getAttribute("id");

        // Vérifiez que l'utilisateur est connecté
        if (login == null || utilisateurId == null) {
            model.addAttribute("message", "Vous devez être connecté pour ajouter un feedback.");
            return "feedback";
        }

        // Vérifiez le nombre de feedbacks existants pour cet utilisateur
        List<Feedback> userFeedbacks = feedbackService.recupererFeedbacksParUtilisateur(utilisateurId);
        if (userFeedbacks.size() >= 5 && session.getAttribute("role")== "ADMIN") {
            model.addAttribute("message", "Vous avez atteint le quota maximum de 5 feedbacks pour un Admin.");
            model.addAttribute("outilId", outilId);
            return "feedback";
        }
        if (userFeedbacks.size() >=2 && session.getAttribute("role")== "ETUDIANT") {
            model.addAttribute("message", "Vous avez atteint le quota maximum de 2 feedbacks pour un Etudiant .");
            model.addAttribute("outilId", outilId);
            return "feedback";
        }
        if (userFeedbacks.size() >= 3 && session.getAttribute("role")== "ENSEIGNANT") {
            model.addAttribute("message", "Vous avez atteint le quota maximum de 3 feedbacks pour un Enseignant .");
            model.addAttribute("outilId", outilId);
            return "feedback";
        }

        Outil outil = outilService.recupererOutilParId(outilId)
                .orElseThrow(() -> new IllegalArgumentException("Outil introuvable"));

        Utilisateur utilisateur = utilisateurService.recupererUtilisateurParLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));

        Feedback feedback = new Feedback();
        feedback.setContenu(contenu);
        feedback.setOutil(outil);
        feedback.setUtilisateur(utilisateur);

        feedbackService.ajouterFeedback(feedback);

        session.setAttribute("message", "Votre feedback a été ajouté avec succès.");
        return "redirect:/outils";
    }

}
