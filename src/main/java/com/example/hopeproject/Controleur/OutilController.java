package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Service.OutilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/outils")
public class OutilController {

    @Autowired
    private OutilService outilService;

    // Affiche la page HTML des outils avec Thymeleaf
    @GetMapping
    public String afficherPageOutils(Model model) {
        model.addAttribute("outils", outilService.recupererTousLesOutils());
        return "outils"; // Correspond au fichier templates/outils.html
    }

    // API REST pour récupérer tous les outils (format JSON)
    @GetMapping("/api")
    @ResponseBody
    public List<Outil> recupererTousLesOutils() {
        return outilService.recupererTousLesOutils();
    }

    // API REST pour récupérer un outil par ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public Outil recupererOutilParId(@PathVariable Long id) {
        return outilService.recupererOutilParId(id).orElse(null);
    }

    // API REST pour ajouter un outil
    @PostMapping("/api")
    @ResponseBody
    public Outil ajouterOutil(@RequestBody Outil outil) {
        return outilService.ajouterOutil(outil);
    }

    // API REST pour supprimer un outil
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void supprimerOutil(@PathVariable Long id) {
        outilService.supprimerOutil(id);
    }
}
