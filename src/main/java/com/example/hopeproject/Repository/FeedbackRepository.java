package com.example.hopeproject.Repository;

import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Modele.Outil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByOutilId(Long outilId);
    List<Feedback> findByUtilisateurId(Long utilisateurId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Feedback f WHERE f.outil.id = :outilId")
    void deleteByOutilId(@Param("outilId") Long outilId);

}


