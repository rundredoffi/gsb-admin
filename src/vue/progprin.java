package vue;
import java.util.ArrayList;

import antlr.collections.List;
import persistance.AccesData;
import persistance.HibernateSession;
import metier.Utilisateur;


public class progprin {
	public static void main(String[] args) {
        Login loginScreen = new Login();
        loginScreen.setVisible(true);
        loginScreen.addLoginListener(utilConnecte -> {
            System.out.println("Utilisateur connecté : " + utilConnecte.getPrenom()+" "+utilConnecte.getNom());
            loginScreen.dispose();// Fermer la fenêtre de login
            new menu(utilConnecte);
            
           
        });
       

        

	}
}
