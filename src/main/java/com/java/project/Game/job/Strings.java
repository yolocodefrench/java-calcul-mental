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
		String printing = "";
		printing += "         ┌─────────────────────────────────────────────────┐\n";
		printing += "         │                                                 │\n";
		printing += "         │               Résolvez ce calcul                │\n";
		printing += "         │                                                 │\n";
		printing += "         │                                                 │\n";
		printing += "         │  Calcul = "+ calcul +"                                       │\n";//TODO Fix string length with private method 
		printing += "         │                                                 │\n";
		printing += "         │  Il vous reste "+ numCalcul +" à exécuter               │\n";
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

}
