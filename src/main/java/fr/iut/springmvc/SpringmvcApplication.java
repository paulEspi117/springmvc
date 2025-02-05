package fr.iut.springmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.iut.springmvc.Entity.Produit;
import fr.iut.springmvc.repository.ProduitRepository;

@SpringBootApplication
public class SpringmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase( ProduitRepository repo){

		
			
			return args ->{
				/*repo.save(new Produit("clavier",39.99,0));
        		repo.save(new Produit("écran",200,10));
				repo.save(new Produit("mathis",1000,2));
        		repo.save(new Produit("brefuel",3000,1));
				repo.save(new Produit("souris USB",14.99,25));
        		repo.save(new Produit("souris BT",18.99,30));*/

				repo.findAll().forEach(System.out::println);
			};
		

	}

}
