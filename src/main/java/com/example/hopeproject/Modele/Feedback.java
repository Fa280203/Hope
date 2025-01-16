package com.example.hopeproject.Modele;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, updatable = false, nullable = false)
    private String uuid;

    private String contenu;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "outil_id")
    private Outil outil;
    @PrePersist
    public void initializeUUID() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }
    private LocalDateTime dateCreation = LocalDateTime.now();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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
