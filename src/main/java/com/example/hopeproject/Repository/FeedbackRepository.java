package com.example.hopeproject.Repository;

import com.example.hopeproject.Modele.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByOutil_Id(Long outilId);
}

