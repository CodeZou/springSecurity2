
package ma.emsi.springSecurity2.controllers;


import ma.emsi.springSecurity2.entities.Product;
import ma.emsi.springSecurity2.repositories.ProductRepository;
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
public class ProductController {
    @Autowired
    private ProductRepository repository;

    public ProductController() {
    }

    @GetMapping(
            path = {"/index"}
    )
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int p, @RequestParam(name = "size",defaultValue = "7") int s, @RequestParam(name = "Keyword",defaultValue = "") String k) {
        Page<Product> pageProducts = this.repository.findByNameContains(k, PageRequest.of(p, s));
        model.addAttribute("listepp", pageProducts.getContent());
        model.addAttribute("pages", new int[pageProducts.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("Keyword", k);
        return "products";
    }

    @GetMapping({"/delete"})
    public String delete(Long id) {
        this.repository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping({"/home"})
    public String home() {
        return "home";
    }

    @GetMapping({"products"})
    public List<Product> lisProducts() {
        return this.repository.findAll();
    }

    @GetMapping({"/formProducts"})
    public String formProducts(Model model) {
        model.addAttribute("product", new Product());
        return "formProducts";
    }

    @PostMapping({"/save"})
    public String save(Model model, Product product) {
        this.repository.save(product);
        return "redirect:/index";
    }

    @GetMapping("/editProducts")
    public String editProducts(Model model, Long id) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
            return "editProducts";
        } else {
            throw new RuntimeException("Produit introuvable");
        }
    }

}
