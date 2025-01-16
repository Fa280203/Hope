package com.example.hopeproject.Service;

import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Repository.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void ajouterFeedback(Feedback feedback) {
        logger.info("Ajout d'un feedback pour l'outil {} par l'utilisateur {}.",
                feedback.getOutil().getId(), feedback.getUtilisateur().getId());
        feedbackRepository.save(feedback);
    }

    public void supprimerFeedback(Long id) {
        logger.info("Suppression du feedback avec ID {}.", id);
        feedbackRepository.deleteById(id);
    }

    public void supprimerFeedbacksParOutilId(Long outilId) {
        logger.info("Suppression de tous les feedbacks pour l'outil avec ID {}.", outilId);
        feedbackRepository.deleteByOutilId(outilId);
    }

    public List<Feedback> recupererFeedbacksParOutil(Long outilId) {
        logger.info("Récupération des feedbacks pour l'outil avec ID {}.", outilId);
        return feedbackRepository.findByOutilId(outilId);
    }

    public boolean existeFeedback(Long id) {
        return feedbackRepository.existsById(id);
    }
    public List<Feedback> recupererFeedbacksParUtilisateurEtOutil(Long utilisateurId, Long outilId) {
        return feedbackRepository.findByUtilisateurIdAndOutilId(utilisateurId, outilId);
    }
}
