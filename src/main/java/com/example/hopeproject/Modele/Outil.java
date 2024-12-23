package com.example.hopeproject.Modele;

import jakarta.persistence.*;

@Entity
public class Outil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre; // Colonne A
    private String domaine; // Colonne B
    private String descriptionSimple; // Colonne C

    @Column(length = 5000)
    private String descriptionDetaillee;
    @Column(length = 5000)

    private String lien; // Colonne F
    @Column(length = 5000)
    private String acces; // Colonne E

    // Constructeur complet (pour tout afficher)
    public Outil(String titre, String domaine, String descriptionSimple, String descriptionDetaillee,String lien, String acces) {
        this.titre = titre;
        this.domaine = domaine;
        this.descriptionSimple = descriptionSimple;
        this.descriptionDetaillee = descriptionDetaillee;
        this.acces = acces;
        this.lien = lien;
    }

    // Constructeur synthétique (pour A, B, C et E uniquement)
    public Outil(String titre, String domaine, String descriptionSimple, String lien) {
        this.titre = titre;
        this.domaine = domaine;
        this.descriptionSimple = descriptionSimple;
        this.lien = lien;
    }

    // Constructeur par défaut (nécessaire pour JPA)
    public Outil() {}

    // Getters et setters pour tous les champs

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDescriptionSimple() {
        return descriptionSimple;
    }

    public void setDescriptionSimple(String descriptionSimple) {
        this.descriptionSimple = descriptionSimple;
    }

    public String getDescriptionDetaillee() {
        return descriptionDetaillee;
    }

    public void setDescriptionDetaillee(String descriptionDetaillee) {
        this.descriptionDetaillee = descriptionDetaillee;
    }

    public String getAcces() {
        return acces;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
}
