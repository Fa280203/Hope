package com.example.hopeproject.Repository;

import com.example.hopeproject.Modele.Outil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutilRepository extends JpaRepository<Outil, Long> {
    List<Outil> findByDomaineContaining(String domaine);
    List<Outil> findByBooleanWithDefaultValue(boolean booleanWithDefaultValue);
    List<Outil> findByTitreContainingIgnoreCaseOrDomaineContainingIgnoreCaseOrDescriptionSimpleContainingIgnoreCase(
            String titre, String domaine, String descriptionSimple);
    List<Outil> findByTitreContainingIgnoreCase(String titre);

    List<Outil> findByDomaineContainingIgnoreCase(String domaine);

    List<Outil> findByDescriptionSimpleContainingIgnoreCase(String descriptionSimple);
    @Query("SELECT DISTINCT o.domaine FROM Outil o WHERE o.domaine IS NOT NULL")
    List<String> findDistinctDomaines();
    // Recherche par titre ou description
    List<Outil> findByTitreContainingIgnoreCaseOrDescriptionSimpleContainingIgnoreCase(String titre, String descriptionSimple);

    // Recherche par domaine uniquement
    List<Outil> findByDomaine(String domaine);

    List<Outil> findByTitreContainingIgnoreCaseAndBooleanWithDefaultValueOrDescriptionSimpleContainingIgnoreCaseAndBooleanWithDefaultValue(
            String titre, boolean booleanWithDefaultValue1, String description, boolean booleanWithDefaultValue2);

    List<Outil> findByDomaineAndBooleanWithDefaultValue(String domaine, boolean booleanWithDefaultValue);

    List<Outil> findByDomaineAndTitreContainingIgnoreCaseAndBooleanWithDefaultValueOrDomaineAndDescriptionSimpleContainingIgnoreCaseAndBooleanWithDefaultValue(
            String domaine, String titre, boolean booleanWithDefaultValue1, String domaine2, String description, boolean booleanWithDefaultValue2);

    // Recherche par domaine et titre/description
    @Query("SELECT o FROM Outil o WHERE o.domaine = :domaine AND (o.titre LIKE %:query% OR o.descriptionSimple LIKE %:query%)")
    List<Outil> findByDomaineAndTitreOrDescription(@Param("domaine") String domaine, @Param("query") String query);


}

