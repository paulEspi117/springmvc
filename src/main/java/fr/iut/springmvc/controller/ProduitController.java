package fr.iut.springmvc.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.iut.springmvc.Entity.Produit;
import fr.iut.springmvc.repository.ProduitRepository;
import org.springframework.ui.Model;


@Controller
public class ProduitController {

    private ProduitRepository repo;

    public ProduitController(ProduitRepository pr){
        this.repo=pr;
    }

    @GetMapping("/produits")
    public String listerProduits(@RequestParam(value = "mc", defaultValue = "") String motsCle,
                                 @RequestParam(value = "p", defaultValue = "0") int page, 
                                 @RequestParam(value = "s", defaultValue = "5") int size,
                                 Model model) 
                                 {
                                    
            Pageable pageable = PageRequest.of(page, size);

            Page<Produit> pageProduit;
            if(motsCle.length()>0){
                pageProduit= this.repo.rechercheParContenu('%'+motsCle+'%', pageable);
            }else{
                pageProduit = this.repo.findAll(pageable);
            }
            

            model.addAttribute("listeProduits", pageProduit.getContent());
            model.addAttribute("page", pageProduit);
            model.addAttribute("mc", motsCle);

            
        return "produits";
    }
    
}
