package fr.iut.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.iut.springmvc.Entity.Produit;
import fr.iut.springmvc.repository.ProduitRepository;


@Controller
public class HomeController {

    

    @GetMapping("/")
    public String home() {
        

        return "home";
    }

    @GetMapping("/cgv")
    public String cgv() {
        return "cgv";
    }
    


    
}
