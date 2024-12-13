package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<Feedback> recupererTousLesFeedbacks() {
        return feedbackService.recupererTousLesFeedbacks();
    }

    @PostMapping
    public Feedback ajouterFeedback(@RequestBody Feedback feedback) {
        return feedbackService.ajouterFeedback(feedback);
    }

    @DeleteMapping("/{id}")
    public void supprimerFeedback(@PathVariable Long id) {
        feedbackService.supprimerFeedback(id);
    }
}