package com.example.hopeproject.Modele;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class Outil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String domaine;
    private String descriptionSimple;

    @Column(length = 5000)
    private String descriptionDetaillee;
    @Column(length = 5000)

    private String lien;
    @Column(length = 5000)
    private String acces;
    @Column(nullable = false) // Ajoutez cette annotation si le champ ne doit pas être null en BDD
    private boolean booleanWithDefaultValue = true;


    public Outil(String titre, String domaine, String descriptionSimple, String descriptionDetaillee,String lien, String acces) {
        this.titre = titre;
        this.domaine = domaine;
        this.descriptionSimple = descriptionSimple;
        this.descriptionDetaillee = descriptionDetaillee;
        this.acces = acces;
        this.lien = lien;
    }


    public boolean isBooleanWithDefaultValue() {
        return booleanWithDefaultValue;
    }

    public void setBooleanWithDefaultValue(boolean booleanWithDefaultValue) {
        this.booleanWithDefaultValue = booleanWithDefaultValue;
    }

    public Outil(String titre, String domaine, String descriptionSimple, String lien) {
        this.titre = titre;
        this.domaine = domaine;
        this.descriptionSimple = descriptionSimple;
        this.lien = lien;
    }

    public Outil() {}


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
