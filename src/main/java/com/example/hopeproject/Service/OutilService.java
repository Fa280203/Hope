package com.example.hopeproject.Service;

import com.example.hopeproject.Exceptions.OutilIntrouvableException;
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

    public Optional<Outil> recupererOutilParUuid(String uuid) {
        return outilRepository.findByUuid(uuid);
    }

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
        if (!outilRepository.existsById(id)) {
            throw new OutilIntrouvableException("Outil avec l'ID " + id + " introuvable.");
        }
        outilRepository.deleteById(id);
    }

    public List<Outil> recupererOutilsValides() {
        return outilRepository.findByBooleanWithDefaultValue(true);
    }

    public List<Outil> recupererOutilsNonValides() {
        return outilRepository.findByBooleanWithDefaultValue(false);
    }

    public List<Outil> rechercherOutils(String query, String domaine) {
        if (query == null && (domaine == null || domaine.isEmpty())) {
            return outilRepository.findByBooleanWithDefaultValue(true);
        } else if (query != null && (domaine == null || domaine.isEmpty())) {
            return outilRepository.findByTitreOrDescriptionSimpleAndBooleanWithDefaultValue(
                    query, true, query, true);
        } else if (query == null) {
            return outilRepository.findByDomaineAndBooleanWithDefaultValue(domaine, true);
        } else {
            return outilRepository.findByDomaineAndTitreContainingOrDomaineAndDescriptionSimple(
                    domaine, query, true, domaine, query, true);
        }
    }

    public List<String> recupererDomaines() {
        return outilRepository.findDistinctDomainesForValidOutils();
    }
}
