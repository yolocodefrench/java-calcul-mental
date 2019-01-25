package com.java.project.Game.job;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.project.Game.dal.IUtilisateurRepository;
import com.java.project.Game.domain.Utilisateur;

public class Menu {
    /** Constructeur privé */
    private Menu()
    {}
    
    @Autowired
    private IUtilisateurRepository daoUtilisateur;
     
    /** Instance unique non préinitialisée */
    private static Menu INSTANCE = null;
     
    /** Point d'accès pour l'instance unique du singleton */
    public static Menu getInstance()
    {           
        if (INSTANCE == null)
        {   INSTANCE = new Menu(); 
        }
        return INSTANCE;
    }
    
    public void printGame() {
    	String replay = "";
    	while(replay != "N") {
    		//Affichage de la fenetre de connexion
    		this.getUser();
    		
    		//Affichage de la fenetre qui demande si le joueur veut rejouer
    		Strings.printReplay();
    	}
    }
    
    private String getUser() {
    	Strings.printConnection();
    	
    	String pseudo = this.getStringFromScanner("Rentrez votre pseudo");    	
    	this.daoUtilisateur.findByPseudo(pseudo);
    	
    	
    	
		return "";
    }
    
    private String getStringFromScanner(String sentence) {
    	if(!sentence.equals("")) {
    		System.out.println(sentence);
    	}
    	Scanner sc = new Scanner(System.in);
    	String str = sc.nextLine();
    	sc.close();
    	return str;
    }
    
    private int getIntFromScanner() {
    	Scanner sc = new Scanner(System.in);
    	int num = sc.nextInt();
    	sc.close();
    	return num;
    }
}
