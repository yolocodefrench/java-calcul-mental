package com.java.project.Game.job;

import java.util.Scanner;

public class Menu {
    /** Constructeur privé */
    private Menu()
    {}
     
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
    	System.out.println("Rentrez votre pseudo");
    	
    	String username = this.getStringFromScanner();
		return username;
    }
    
    private String getStringFromScanner() {
    	Scanner sc = new Scanner(System.in);
    	String str = sc.nextLine();
    	sc.close();
    	return str;
    }
}
