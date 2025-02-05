package fr.iut.springmvc.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.iut.springmvc.Entity.*;
import java.util.List;


public interface ProduitRepository extends JpaRepository<Produit, Integer> {

    @Query("SELECT p FROM Produit p WHERE p.designation LIKE :mots")
    Page<Produit> rechercheParContenu(@Param("mots")String mc, Pageable p);


    //Méthodes auto-générées
    List<Produit> findByQuantite(int quantite);
    List<Produit> findByQuantite(int quantite, Pageable p);

    
}
