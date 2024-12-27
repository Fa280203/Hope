package com.example.hopeproject.Modele;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "outil_id")
    private Outil outil;

    private LocalDateTime dateCreation = LocalDateTime.now();

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getContenu() {
        return contenu;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public Outil getOutil() {
        return outil;
    }

    public void setOutil(Outil outilId) {
        this.outil = outilId;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    // Getters and Setters
}
