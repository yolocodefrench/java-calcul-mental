package com.java.project.Game.job;

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
}
