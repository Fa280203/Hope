package com.example.hopeproject.Controleur;

import com.example.hopeproject.Modele.Feedback;
import com.example.hopeproject.Modele.Outil;
import com.example.hopeproject.Service.FeedbackService;
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
    @Autowired
    private FeedbackService feedbackService;
    // Affiche la page HTML des outils avec Thymeleaf
    @GetMapping
    public String afficherPageOutils(Model model, HttpSession session) {
        // Récupère les informations de la session
        String nom = (String) session.getAttribute("nom");
        String prenom = (String) session.getAttribute("prenom");
        List<String> domaines = outilService.recupererDomaines();

        System.out.println(nom);
        System.out.println(prenom);
        // Ajoute les données au modèle pour les afficher
        model.addAttribute("nom", nom);
        model.addAttribute("prenom", prenom);
        model.addAttribute("outils", outilService.recupererOutilsValides());
        model.addAttribute("domaines", domaines);


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
    @PostMapping("/supprimer/{id}")
    public String supprimerOutil(@PathVariable Long id) {
        feedbackService.supprimerFeedbacksParOutilId(id);

        outilService.supprimerOutil(id);

        return "redirect:/outils";
    }



    @GetMapping("/details/{id}")
    public String afficherDetailsOutil(@PathVariable Long id, Model model) {
        Outil outil = outilService.recupererOutilParId(id).orElse(null);
        List<Feedback> feedbacks = feedbackService.recupererFeedbacksParOutil(id);

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

        model.addAttribute("feedbacks", feedbacks); // Ajouter feedbacks au modèle

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
    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("outil", new Outil()); // Crée un objet vide pour le formulaire
        return "ajouter"; // Fichier templates/ajouter.html
    }

    // Gérer la soumission du formulaire d'ajout
    @PostMapping("/ajouter")
    public String ajouterOutil(
            @RequestParam String titre,
            @RequestParam String domaine,
            @RequestParam String descriptionSimple,
            @RequestParam String lien,
            @RequestParam String acces,
            HttpSession session
    ) {
        String role = (String) session.getAttribute("role");

        Outil nouvelOutil = new Outil();
        nouvelOutil.setTitre(titre);
        nouvelOutil.setDomaine(domaine);
        nouvelOutil.setDescriptionSimple(descriptionSimple);
        nouvelOutil.setLien(lien);
        nouvelOutil.setAcces(acces);

        // Définit `booleanWithDefaultValue` selon le rôle
        if ("ADMIN".equals(role)) {
            nouvelOutil.setBooleanWithDefaultValue(true); // Ajout direct
        } else {
            nouvelOutil.setBooleanWithDefaultValue(false); // En attente de validation
        }

        outilService.ajouterOutil(nouvelOutil);
        return "redirect:/outils";
    }
    @GetMapping("/admin/validation")
    public String afficherOutilsNonValides(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");

        // Vérifiez si l'utilisateur est admin
        if (!"ADMIN".equals(role)) {
            return "redirect:/outils"; // Redirige si non admin
        }

        List<Outil> outilsNonValides = outilService.recupererOutilsNonValides();
        model.addAttribute("outilsNonValides", outilsNonValides);

        return "adminValidation"; // Correspond au fichier templates/adminValidation.html
    }
    @PostMapping("valider/{id}")
    public String validerOutil(@PathVariable Long id){
        var outil = outilService.recupererOutilParId(id)
                .orElseThrow(() -> new IllegalArgumentException("Outil introuvable"));
        outil.setBooleanWithDefaultValue(true);
        outilService.ajouterOutil(outil);

        return "redirect:/outils";

    }
    @GetMapping("/recherche")
    public String rechercherOutils(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String domaine,
            Model model,
            HttpSession session
    ) {
        // Récupère les informations de la session
        String nom = (String) session.getAttribute("nom");
        String prenom = (String) session.getAttribute("prenom");

        // Ajoute les données au modèle
        model.addAttribute("nom", nom);
        model.addAttribute("prenom", prenom);

        // Recherche avancée
        List<Outil> resultats = outilService.rechercherOutils(query, domaine);

        model.addAttribute("outils", resultats);
        model.addAttribute("domaines", outilService.recupererDomaines()); // Garde les domaines pour la liste déroulante

        return "outils"; // Retourne la même page avec les résultats filtrés
    }






}

