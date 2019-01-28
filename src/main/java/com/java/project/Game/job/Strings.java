package com.java.project.Game.job;

import com.java.project.Game.domain.Partie;

public class Strings {
	
	public static void printConnection() {
		String printing = "";
		printing += "         ┌─────────────────────────────────────────────────┐\n";
		printing += "         │                                                 │\n";
		printing += "         │                                                 │\n";
		printing += "         │                  Se connecter                   │\n";
		printing += "         │                                                 │\n";
		printing += "         │                                                 │\n";
		printing += "         └─────────────────────────────────────────────────┘\n";

		System.out.println(printing);
	}
	public static void printCalcul(String calcul, int numCalcul) {
		
		String avant = "";
		String apres = "";
		
		StringBuilder sb = new StringBuilder();
		String printing = "";
		
		for(int i = 0;i < (50-calcul.length())/2;i++){
			sb.append(" ");
		}
		
		avant = sb.toString();
		
		apres = avant;
		
		
		if(avant.length()*2+calcul.length()>50) {
			 int res = avant.length()*2+calcul.length() - 50;
			 apres.substring(res);
		}
		
		String derive = numCalcul > 9 ? "" : " ";
		printing += "         ┌─────────────────────────────────────────────────┐\n";
		printing += "         │                                                 │\n";
		printing += "         │               Résolvez ce calcul                │\n";
		printing += "         │                                                 │\n";
		printing += "         │                                                 │\n";
		printing += "         │"+ avant + calcul + apres + "│\n";//TODO Fix string length with private method 
		printing += "         │                                                 │\n";
		printing += "         │          Il vous reste "+ numCalcul +" à exécuter" + derive + "            │\n";
		printing += "         └─────────────────────────────────────────────────┘\n";
		System.out.println(printing);
	}
	public static void print10BestScores(Partie[] partie) {
		
		String printing = "";
		printing += "         ┌─────────────────────────────────────────────────┐\n";
		printing += "         │                                                 │\n";
		printing += "         │           Voici les 10 meilleurs scores         │\n";
		printing += "         │                                                 │\n";
		for(Partie p : partie) {
			printing += "│   "+p.getUtilisateur() + " : " + p.getScore() + "           |\n";
		}
		printing += "         └─────────────────────────────────────────────────┘\n";
		System.out.println("");
	}
	public static void printReplay() {
		String printing = "";
		printing += "         ┌─────────────────────────────────────────────────┐\n";
		printing += "         │                                                 │\n";
		printing += "         │                                                 │\n";
		printing += "         │                     Rejouer                     │\n";
		printing += "         │                                                 │\n";
		printing += "         │                                                 │\n";
		printing += "         │         Oui : O                 Non : N         │\n";
		printing += "         │                                                 │\n";
		printing += "         └─────────────────────────────────────────────────┘\n";

		System.out.println(printing);
	}
	
	public static void printIdentityAsk() {
		String printing = "";
		printing += "         ┌─────────────────────────────────────────────────┐\n";
		printing += "         │                                                 │\n";
		printing += "         │     Un utilisateur avec ce nom existe déjà.     │\n";
		printing += "         │         Voulez-vous vraiment utiliser           │\n";
		printing += "         │                 cette identité                  │\n";
		printing += "         │                                                 │\n";
		printing += "         │         Oui : O                 Non : N         │\n";
		printing += "         │                                                 │\n";
		printing += "         └─────────────────────────────────────────────────┘\n";

		System.out.println(printing);
	}

}
