package com.example.hopeproject.Modele;

import jakarta.persistence.*;

@Entity
public class Outil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String domaine;
    private String descriptionSimple;
    private String descriptionDetaillee;
    private String lien;
    private String acces;


}
