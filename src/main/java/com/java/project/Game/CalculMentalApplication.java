package com.java.project.Game;

import com.java.project.Game.dal.IPartieRepository;
import com.java.project.Game.dal.IUtilisateurRepository;
import com.java.project.Game.domain.Calcul;
import com.java.project.Game.domain.Partie;
import com.java.project.Game.domain.Utilisateur;
import com.java.project.Game.job.Strings;
import com.java.project.Game.utils.ScannerUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculMentalApplication implements CommandLineRunner{
	/*
	 * Suite à un souci de configuration surement et un certain manque de compétence dans le domaine
	 * Nous n'avons pas réussi à utiliser les services et l'autowiring correctement, ce qui fait que nous ne 
	 * pouvions utiliser les requetes de nos répositories en dehors de cette classe
	 * Nous avons donc mis tout le fonctionnement de base del'application dans cette classe 
	*/
	
	@Autowired
	IUtilisateurRepository daoUtilisateur;
	
	@Autowired
	IPartieRepository daoPartie;
	public static void main(String[] args) {
		SpringApplication.run(CalculMentalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		int MAX_NBR_TOUR = 2;

		String replay = "";
		int nbrTour = 0;
		String operation = "";
		int score = 0;
    	while(!replay.equals("N")) {

    		//Affichage de la fenetre de connexion
    		Utilisateur user = this.getUser();
    		
    		
    		//Boucle de jeu
    		while(nbrTour < MAX_NBR_TOUR) {
    			Calcul calcul = new Calcul();
    			calcul.generateCalcul(5);
    			//Récupération du calcul qui a été généré
    			operation = calcul.getCalcul();
    			Strings.printCalcul(operation, (MAX_NBR_TOUR-nbrTour));
    			String[] arrayRes = {"", "!"};
    			String resultat = this.getStringFromScanner("", arrayRes);
				
				resultat = resultat.replace(" ", "");
    			resultat = resultat.replaceAll(",", ".");

    			//replace string response to float if it's possible
				try {

					if(calcul.isEqual(resultat)) {
						System.out.println("Bonne réponse");
						score++;
					}
					else {
						System.out.println("Mauvaise réponse");
					}

				}catch (Exception e){
					System.out.println("This result is not a number !!");
				}
    			


    			nbrTour++;
    			
    			
				if (nbrTour ==  MAX_NBR_TOUR) {

					//Récupération du meilleur score de l'utilisateur
					Partie bestGame = daoPartie.findFirstByUtilisateurOrderByScoreDesc(user);
					int bestScore = bestGame == null ? -1 : bestGame.getScore();
					Strings.printScoreAndBestScore(score, bestScore);

					//Enregistrement de la partie en base de données
					Partie partie = new Partie();
					partie.setScore(score);
					partie.setUtilisateur(user);
					daoPartie.save(partie);
				}
    		}
    		
    		nbrTour = 0;
    		
    		//Récupération et affichage des 10 meilleurs scores
    		Strings.print10BestScores(daoPartie.findTop10ByOrderByScoreDesc());
    		
    		//Affichage de la fenetre qui demande si le joueur veut rejouer
    		Strings.printReplay();
			String[] replayArray = {"O", "N"};

			replay = this.getStringFromScanner("", replayArray);
    	}
		
	}
	
	//Récupération des informations de l'utilisateur
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
    
	/*Cette méthode permet d'éviter les différents problèmes liés aux scanner, de cette manière, 
	 * nous n'en avons qu'un seul pour tout le projet
	*/
	private String getStringFromScanner(String sentence, String... param) {
    	if(!sentence.equals("")) {
    		System.out.println(sentence);
    	}
    	
    	Scanner sc = ScannerUtil.getInstance();
    	String str = sc.nextLine();
    	
    	/*
    	 * Si il y a des params spécifiés et param pas null
    	*/
    	if(param.length > 0 && param!=null) {
    		//Si le dernier n'est pas égal à !
    		if(!param[param.length-1].equals("!")) {
    			for(String s: param) {
            		if(s.equals(str)) {
            			return str;
            		}	
            	}
        		System.out.println("Veuillez saisir une réponse correcte");
        		return this.getStringFromScanner(sentence, param);
        	}
        	else {
        		for(String s: param) {
        			int i=0;
            		if(!s.equals(str)  && i!=param.length-1) {
            			return str;
            		}
            		i++;
            	}
        		System.out.println("Veuillez saisir une réponse correcte 1");
        		return this.getStringFromScanner(sentence, param);
        	}
    	}
    	return str;
    }

}

	

