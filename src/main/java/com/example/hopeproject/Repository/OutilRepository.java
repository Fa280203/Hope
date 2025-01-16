package com.example.hopeproject.Repository;

import com.example.hopeproject.Modele.Outil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

public interface OutilRepository extends JpaRepository<Outil, Long> {
    Optional<Outil> findByUuid(String uuid);

    List<Outil> findByBooleanWithDefaultValue(boolean booleanWithDefaultValue);

    default List<Outil> findByTitreOrDescriptionSimpleAndBooleanWithDefaultValue(
            String titre, boolean booleanWithDefaultValue1, String description, boolean booleanWithDefaultValue2) {
        return null;
    }

    List<Outil> findByDomaineAndBooleanWithDefaultValue(String domaine, boolean booleanWithDefaultValue);

    default List<Outil> findByDomaineAndTitreContainingOrDomaineAndDescriptionSimple(
            String domaine1, String titre, boolean booleanWithDefaultValue1, String domaine2, String description, boolean booleanWithDefaultValue2) {
        return null;
    }


    @Query("SELECT DISTINCT o.domaine FROM Outil o WHERE o.booleanWithDefaultValue = true AND o.domaine IS NOT NULL")
    List<String> findDistinctDomainesForValidOutils();


}
