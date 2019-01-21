package com.java.project.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.project.Game.dal.IUtilisateurRepository;
import com.java.project.Game.domain.Utilisateur;

@SpringBootApplication
public class CalculMentalApplication implements CommandLineRunner{
	
	@Autowired
	IUtilisateurRepository daoUtilisateur;
	
	public static void main(String[] args) {
		SpringApplication.run(CalculMentalApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Utilisateur utilisateur = new Utilisateur("Foglol");
		daoUtilisateur.save(utilisateur);
		
		
	}

}

	

