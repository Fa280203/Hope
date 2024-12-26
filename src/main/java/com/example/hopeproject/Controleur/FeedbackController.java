package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/formulaire/{outilId}")
    public String afficherFormulaireFeedback(@PathVariable Long outilId, Model model) {
        model.addAttribute("outilId", outilId);
        return "feedback"; // Correspond au fichier templates/feedbackForm.html
    }

    @PostMapping("/ajouter")
    public String ajouterFeedback(@RequestParam String contenu, @RequestParam Outil outilId) {
        Feedback feedback = new Feedback();
        feedback.setContenu(contenu);
        feedback.setOutil(outilId);
        feedbackService.ajouterFeedback(feedback);
        return "redirect:/outils"; // Redirige vers la liste des outils après ajout
    }
}
