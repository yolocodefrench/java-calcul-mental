package com.java.project.Game;

import com.java.project.Game.dal.IPartieRepository;
import com.java.project.Game.dal.IUtilisateurRepository;
import com.java.project.Game.domain.Calcul;
import com.java.project.Game.domain.Partie;
import com.java.project.Game.domain.Utilisateur;
import com.java.project.Game.job.Strings;
import com.java.project.Game.utils.ScannerUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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

		int MAX_NBR_TOUR = 1;

		String replay = "";
		int nbrTour = 0;
		String operation = "";
		int score = 0;
    	while(!replay.equals("N")) {
    		//Affichage de la fenetre de connexion
    		Utilisateur user = this.getUser();
    		
    		while(nbrTour < MAX_NBR_TOUR) {
    			Calcul calcul = new Calcul();
    			calcul.generateCalcul(5);
    			operation = calcul.getCalcul();
    			Strings.printCalcul(operation, (MAX_NBR_TOUR-nbrTour));
    			String[] arrayRes = {"", "!"};
    			String resultat = this.getStringFromScanner("", arrayRes);
    			
    			resultat = resultat.replace(" ", "");
    			resultat = resultat.replaceAll(",", ".");
    			
    			if(calcul.isEqual(resultat)) {
    				System.out.println("Bonne réponse");
    				score++;
    			}
    			else {
    				System.out.println("Mauvaise réponse");
    			}

    			nbrTour++;

				if (nbrTour ==  MAX_NBR_TOUR) {
					Partie partie = new Partie();
					partie.setScore(score);
					partie.setUtilisateur(user);
					daoPartie.save(partie);
				}
    		}
    		
    		nbrTour = 0;
    		
    		//Affichage de la fenetre qui demande si le joueur veut rejouer
    		Strings.printReplay();
			String replayAsk = null;
			String[] replayArray = {"O", "N"};

			replay = this.getStringFromScanner("", replayArray);
			//ne marche pas
			if (replay.equals("N")){
				Strings.print10BestScores(daoPartie.findTop10ByOrderByScoreDesc());
			}
    	}
		
	}
	
	private Utilisateur getUser() {
    	Strings.printConnection();

    	String name = "";
    	Utilisateur user = null;

    	List<Utilisateur> listUser = new ArrayList<Utilisateur>();

    	while(user == null) {
    		name = this.getStringFromScanner("Rentrez votre pseudo qui doit contenir moins de 20 caractères");
    		if(name.length() > 20) {
    			name = "";
    		}

	   		listUser = this.daoUtilisateur.findByName(name);

	   		if(listUser.size() > 0) {
	   			String[] arrayConfirm = {"O", "N"};
	   			Strings.printIdentityAsk();
	   			String reponse = this.getStringFromScanner("", arrayConfirm);
	   			if(reponse.equals("O")) {
	   				user = listUser.get(0);
	   			}
	   			else {
	   				user = null;
	   			}
	   		}
	   		else {
	   			user = new Utilisateur(name);
	   			this.daoUtilisateur.save(user);
	   		}

    	}
		return user;
    }
    
    private String getStringFromScanner(String sentence, String... param) {
    	if(!sentence.equals("")) {
    		System.out.println(sentence);
    	}
    	
    	Scanner sc = ScannerUtil.getInstance();
    	String str = sc.nextLine();
    	
    	if(param.length > 0 && param!=null) {
    		if(!param[param.length-1].equals("!")) {
    			int i=0;
    			for(String s: param) {
            		if(s.equals(str) && i!=param.length-1) {
            			return str;
            		}
            		i++;
            	}
        		System.out.println("Veuillez saisir une réponse correcte");
        		return this.getStringFromScanner(sentence, param);
        	}
        	else {
        		for(String s: param) {
            		if(!s.equals(str) ) {
            			return str;
            		}
            	}
        		System.out.println("Veuillez saisir une réponse correcte");
        		return this.getStringFromScanner(sentence, param);
        	}
    	}
    	return str;
    }
    
    private int getIntFromScanner() {
    	Scanner sc = ScannerUtil.getInstance();
    	int num = sc.nextInt();
    	sc.close();
    	return num;
    }

}

	

