package com.example.hopeproject.Service;

import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Repository.OutilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutilService {

    @Autowired
    private OutilRepository outilRepository;

    public List<Outil> recupererTousLesOutils() {
        return outilRepository.findAll();
    }

    public Optional<Outil> recupererOutilParId(Long id) {
        return outilRepository.findById(id);
    }

    public Outil ajouterOutil(Outil outil) {
        return outilRepository.save(outil);
    }

    public void supprimerOutil(Long id) {
        outilRepository.deleteById(id);
    }
}
