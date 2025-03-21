package vue;
import persistance.HibernateSession;

import java.util.List;

import metier.Utilisateur;
import persistance.AccesData;

public class progprin {
	public static void main(String[] args) {
		List<Utilisateur> unUtilisateur = AccesData.getLesUtilisateur();
		System.out.println(unUtilisateur.toString());
		System.out.println("******************************");
		
        new Login().setVisible(true);
        
        Utilisateur util = AccesData.getUtilisateurByLoginAndMdp("lvillachane", "dqdqzqzd");
		System.out.println(util.toString());

        


	}

}
