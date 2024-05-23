package ma.emsi.springSecurity2.controllers;

import ma.emsi.springSecurity2.entities.Fournisseure;
import ma.emsi.springSecurity2.repositories.FournisseureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fournisseure")
public class FournisseureRestApiController {
    @Autowired
    private FournisseureRepository repository;


    @GetMapping
    public ResponseEntity<List<Fournisseure>> getAllFournisseurs() {
        List<Fournisseure> fournisseurs = repository.findAll();
        return new ResponseEntity<>(fournisseurs, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<Page<Fournisseure>> getFournisseurs(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<Fournisseure> fournisseurs = repository.findByNameContains(keyword, PageRequest.of(page, size));
        return new ResponseEntity<>(fournisseurs, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Fournisseure> getFournisseurById(@PathVariable Long id) {
        Optional<Fournisseure> fournisseur = repository.findById(id);
        return fournisseur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Fournisseure> addFournisseur(@RequestBody Fournisseure fournisseur) {
        Fournisseure savedFournisseur = repository.save(fournisseur);
        return new ResponseEntity<>(savedFournisseur, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Fournisseure> updateFournisseur(@PathVariable Long id, @RequestBody Fournisseure fournisseur) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fournisseur.setId(id); // Ensure the fournisseur ID is set correctly
        Fournisseure updatedFournisseur = repository.save(fournisseur);
        return new ResponseEntity<>(updatedFournisseur, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
