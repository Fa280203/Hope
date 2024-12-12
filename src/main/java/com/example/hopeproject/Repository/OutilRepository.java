package com.example.hopeproject.Repository;

import com.example.hopeproject.Modele.Outil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutilRepository extends JpaRepository<Outil, Long> {
    List<Outil> findByDomaineContaining(String domaine);
}

