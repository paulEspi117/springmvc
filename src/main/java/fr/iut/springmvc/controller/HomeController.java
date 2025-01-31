package fr.iut.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
