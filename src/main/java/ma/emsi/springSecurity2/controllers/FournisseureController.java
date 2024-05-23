package ma.emsi.springSecurity2.controllers;

import ma.emsi.springSecurity2.entities.Fournisseure;
import ma.emsi.springSecurity2.repositories.FournisseureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class FournisseureController {
    @Autowired
    private FournisseureRepository repository;

    public FournisseureController() {
    }

    @GetMapping(
            path = {"/zouhair"}
    )
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int p, @RequestParam(name = "size",defaultValue = "7") int s, @RequestParam(name = "Keyword",defaultValue = "") String k) {
        Page<Fournisseure> PageFournisseures = this.repository.findByNameContains(k, PageRequest.of(p, s));
        model.addAttribute("fournis", PageFournisseures.getContent());
        model.addAttribute("pages", new int[PageFournisseures.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("Keyword", k);
        return "fournisseure";
    }

    @GetMapping({"/supprimer"})
    public String supprimer(Long id) {
        this.repository.deleteById(id);
        return "redirect:/zouhair";
    }

    @GetMapping({"fournisseure"})
    public List<Fournisseure> lisFournisseure() {
        return this.repository.findAll();
    }

    @GetMapping({"/formFournisseure"})
    public String formFournisseure(Model model) {
        model.addAttribute("four", new Fournisseure());
        return "formFournisseure";
    }

    @PostMapping({"/sauve"})
    public String sauve(Model model, Fournisseure fournisseure) {
        this.repository.save(fournisseure);
        return "redirect:/zouhair";
    }

    @GetMapping("/editFournisseure")
    public String editFournisseure(Model model, Long id) {
        Optional<Fournisseure> optionalFournisseure = this.repository.findById(id);
        if (optionalFournisseure.isPresent()) {
            Fournisseure fournisseure = optionalFournisseure.get();
            model.addAttribute("fournisseure", fournisseure);
            return "editFournisseure";
        } else {
            throw new RuntimeException("Fournisseur introuvable");
        }
    }
}

