package com.example.hopeproject.Service;

import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> recupererTousLesFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback ajouterFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public void supprimerFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
