package com.java.project.Game;

import com.java.project.Game.domain.Calcul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.project.Game.dal.IPartieRepository;
import com.java.project.Game.dal.IUtilisateurRepository;
import com.java.project.Game.domain.Partie;
import com.java.project.Game.domain.Utilisateur;

@SpringBootApplication
public class CalculMentalApplication implements CommandLineRunner{
	
	@Autowired
	IUtilisateurRepository daoUtilisateur;
	
	@Autowired
	IPartieRepository daoPartie;
	
	public static void main(String[] args) {
		SpringApplication.run(CalculMentalApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Utilisateur utilisateur = new Utilisateur("Foglol");
		daoUtilisateur.save(utilisateur);

		Partie partie = new Partie(2000, utilisateur);
		daoPartie.save(partie);

		Calcul calcul = new Calcul();
		calcul.generateCalcul(5);
		System.out.println(calcul.getCalcul());



	}

}

	

