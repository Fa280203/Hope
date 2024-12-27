package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Service.OutilService;
import jakarta.servlet.http.HttpSession;
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
    public String afficherPageOutils(Model model, HttpSession session) {
        // Récupère les informations de la session
        String nom = (String) session.getAttribute("nom");
        String prenom = (String) session.getAttribute("prenom");
        System.out.println(nom);
        System.out.println(prenom);
        // Ajoute les données au modèle pour les afficher
        model.addAttribute("nom", nom);
        model.addAttribute("prenom", prenom);
        model.addAttribute("outils", outilService.recupererTousLesOutils());

        return "outils"; // Correspond à `templates/outils.html`
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

    @GetMapping("/details/{id}")
    public String afficherDetailsOutil(@PathVariable Long id, Model model) {
        Outil outil = outilService.recupererOutilParId(id).orElse(null);

        if (outil == null) {
            model.addAttribute("message", "L'outil demandé est introuvable.");
            return "error"; // Assurez-vous que templates/error.html existe
        }

        model.addAttribute("titre", outil.getTitre());
        model.addAttribute("descriptionDetaillee", outil.getDescriptionDetaillee());
        model.addAttribute("acces", outil.getAcces());
        model.addAttribute("descriptionSimple", outil.getDescriptionSimple());
        model.addAttribute("domaine", outil.getDomaine());
        model.addAttribute("lien", outil.getLien());


        model.addAttribute("outilId", outil.getId());
        return "details"; // Correspond au fichier templates/details.html
    }
    // Afficher le formulaire de modification depuis les détails
    @GetMapping("/modifier/{id}")
    public String afficherPageModifierOutil(@PathVariable Long id, Model model) {
        var outil = outilService.recupererOutilParId(id)
                .orElseThrow(() -> new IllegalArgumentException("Outil introuvable"));

        model.addAttribute("outil", outil); // Passe l'objet Outil à la vue
        return "modifier"; // Renvoie le fichier modifier.html
    }


    // Gérer la soumission du formulaire de modification
    @PostMapping("/modifier")
    public String modifierOutil(
            @RequestParam Long id,
            @RequestParam String titre,
            @RequestParam String domaine,
            @RequestParam String descriptionSimple,
            @RequestParam String lien,
            @RequestParam String acces
    ) {
        // Récupération de l'outil existant
        var outil = outilService.recupererOutilParId(id)
                .orElseThrow(() -> new IllegalArgumentException("Outil introuvable"));

        // Mise à jour des champs
        outil.setTitre(titre);
        outil.setDomaine(domaine);
        outil.setDescriptionSimple(descriptionSimple);
        outil.setLien(lien);
        outil.setAcces(acces); // Mise à jour de l'accès

        // Enregistrement dans la base de données
        outilService.ajouterOutil(outil);

        return "redirect:/outils"; // Redirection vers la liste après modification
    }


}

