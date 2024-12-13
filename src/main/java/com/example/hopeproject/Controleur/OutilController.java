package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Service.OutilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outils")
public class OutilController {

    @Autowired
    private OutilService outilService;

    @GetMapping
    public List<Outil> recupererTousLesOutils() {
        return outilService.recupererTousLesOutils();
    }

    @GetMapping("/{id}")
    public Outil recupererOutilParId(@PathVariable Long id) {
        return outilService.recupererOutilParId(id).orElse(null);
    }

    @PostMapping
    public Outil ajouterOutil(@RequestBody Outil outil) {
        return outilService.ajouterOutil(outil);
    }

    @DeleteMapping("/{id}")
    public void supprimerOutil(@PathVariable Long id) {
        outilService.supprimerOutil(id);
    }
}
