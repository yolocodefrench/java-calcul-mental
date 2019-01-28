package com.java.project.Game.utils;

import java.util.Scanner;

public class ScannerUtil {
	
	/** Instance unique non préinitialisée */  
    private static Scanner INSTANCE = null;
     
    /** Point d'accès pour l'instance unique du singleton */
    public static Scanner getInstance()
    {           
        if (INSTANCE == null)
        {   INSTANCE = new Scanner(System.in); 
        }
        return INSTANCE;
    }
    
    public void close() {
    	this.INSTANCE.close();
    }
}
