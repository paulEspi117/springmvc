package fr.iut.springmvc.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.iut.springmvc.Entity.Produit;
import fr.iut.springmvc.repository.ProduitRepository;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
                                 @RequestParam(value = "id", defaultValue = "0") Integer id,
                                 @RequestParam(value = "act", defaultValue = "") String act,
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

            if (id>0 && ("new".equals(act) || "mod".equals(act))) {
                this.repo.findById(id).ifPresent(
                    prod -> {
                        model.addAttribute("produit", prod);
                        model.addAttribute("act", act);
                    }
                );
            } else if ("del".equals(act)) {
                model.addAttribute("act", act);
                
            }

            
        return "produits";
    }

    @GetMapping("/produitEdit")
    public String editerProduit(@RequestParam(value = "mc", defaultValue = "") String motsCle,
                                @RequestParam(value = "p", defaultValue = "0") int page, 
                                @RequestParam(value = "s", defaultValue = "5") int size,
                                @RequestParam(value = "id", defaultValue = "0") Integer id,
                                Model model 
                                ) {
            if (id>0) {
                Optional<Produit> optPro = this.repo.findById(id);
                if (optPro.isPresent()) {
                    model.addAttribute("produit", optPro.get());  
                }else {
                    return "redirect:/produits";
                }
                
            } else {
                model.addAttribute("produit", new Produit());
            }

            model.addAttribute("mc", motsCle);
            model.addAttribute("p", page);
            model.addAttribute("s", size);
        
            return "produitEdit";
    }

    @PostMapping("/produitSave")
    public String sauverProduit(
        int s, int p, String mc, 
        @Valid Produit produit,
        BindingResult bindingResult, //doit être juste après l'objet à valider
        Model model
    ) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("mc", mc);
            model.addAttribute("p", p);
            model.addAttribute("s", s);
            return "produitEdit";
        }

        String action = (produit.getId()==null ? "new" : "mod");

        this.repo.save(produit);
        return "redirect:/produits?p="+p+"&s="+s+"&mc="+mc+"&act="+action+"&id="+produit.getId();

        
    }

    @GetMapping("/produitDelete")
    public String deleteProduit(
        int s, int p, String mc,  RedirectAttributes redirectAttributes, Integer id 
    ) {

        this.repo.deleteById(id);
        redirectAttributes.addAttribute("p", p);
        redirectAttributes.addAttribute("s", s);

        redirectAttributes.addAttribute("mc", mc);
        redirectAttributes.addAttribute("act", "del");


        return "redirect:/produits";
    }
    
    
    
}
