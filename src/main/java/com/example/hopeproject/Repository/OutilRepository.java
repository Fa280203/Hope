package com.example.hopeproject.Repository;

import com.example.hopeproject.Modele.Outil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutilRepository extends JpaRepository<Outil, Long> {

    // Récupérer les outils par domaine contenant une chaîne spécifique
    List<Outil> findByDomaineContainingIgnoreCase(String domaine);

    // Récupérer les outils validés ou non
    List<Outil> findByBooleanWithDefaultValue(boolean booleanWithDefaultValue);

    // Rechercher par titre, domaine ou description (non sensible à la casse)
    List<Outil> findByTitreContainingIgnoreCaseOrDomaineContainingIgnoreCaseOrDescriptionSimpleContainingIgnoreCase(
            String titre, String domaine, String descriptionSimple);

    // Rechercher par titre uniquement
    List<Outil> findByTitreContainingIgnoreCase(String titre);

    // Rechercher par description uniquement
    List<Outil> findByDescriptionSimpleContainingIgnoreCase(String descriptionSimple);

    // Rechercher par domaine uniquement
    List<Outil> findByDomaine(String domaine);

    // Rechercher par titre ou description pour les outils validés
    List<Outil> findByTitreContainingIgnoreCaseAndBooleanWithDefaultValueOrDescriptionSimpleContainingIgnoreCaseAndBooleanWithDefaultValue(
            String titre, boolean booleanWithDefaultValue1, String description, boolean booleanWithDefaultValue2);

    // Rechercher par domaine pour les outils validés
    List<Outil> findByDomaineAndBooleanWithDefaultValue(String domaine, boolean booleanWithDefaultValue);

    // Recherche avancée par domaine et titre ou description (uniquement pour les outils validés)
    List<Outil> findByDomaineAndTitreContainingIgnoreCaseAndBooleanWithDefaultValueOrDomaineAndDescriptionSimpleContainingIgnoreCaseAndBooleanWithDefaultValue(
            String domaine1, String titre, boolean booleanWithDefaultValue1, String domaine2, String description, boolean booleanWithDefaultValue2);

    // Requête personnalisée : rechercher par domaine et mot-clé (titre ou description)
    @Query("SELECT o FROM Outil o WHERE o.domaine = :domaine AND (LOWER(o.titre) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(o.descriptionSimple) LIKE LOWER(CONCAT('%', :query, '%'))) AND o.booleanWithDefaultValue = true")
    List<Outil> findByDomaineAndTitreOrDescription(@Param("domaine") String domaine, @Param("query") String query);

    // Requête pour récupérer les domaines distincts des outils validés
    @Query("SELECT DISTINCT o.domaine FROM Outil o WHERE o.booleanWithDefaultValue = true AND o.domaine IS NOT NULL")
    List<String> findDistinctDomainesForValidOutils();

    // Requête pour récupérer les domaines distincts (tous outils)
    @Query("SELECT DISTINCT o.domaine FROM Outil o WHERE o.domaine IS NOT NULL")
    List<String> findDistinctDomaines();
}
