package fr.iut.springmvc.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 30, message = "NOOON")
    private String designation;

    @NotNull
    @DecimalMin(value="0.01", message="T'es mauvais ")
    private double prix;

    @NotNull
    @DecimalMin(value="0", message = "La quantité ne peut pas être négative ")
    private int quantite;

    
    
    public Produit( String designation, double prix, int quantite) {
        this.id = null;
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit() {
        this("", 0.0, 0);
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit [id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite=" + quantite + "]";
    }

    
    
}
