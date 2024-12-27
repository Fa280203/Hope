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

        Feedback feedback = new Feedback();
        System.out.println(contenu);
        feedback.setContenu(contenu);
        System.out.println(feedback.getContenu());
        String login = (String) session.getAttribute("login");

        // Récupération de l'entité Outil
        Outil outil = outilService.recupererOutilParId(outilId)
                .orElseThrow(() -> new IllegalArgumentException("Outil introuvable"));
        feedback.setOutil(outil);

        // Récupération de l'entité Utilisateur par login
        Utilisateur utilisateur = utilisateurService.recupererUtilisateurParLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
        feedback.setUtilisateur(utilisateur);

        feedbackService.ajouterFeedback(feedback);
        return "redirect:/outils"; // Redirection après ajout du feedback
    }
}
