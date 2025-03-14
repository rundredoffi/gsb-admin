package persistance;

import java.util.List;

import org.hibernate.Session;

import metier.Utilisateur;

public class AccesData {

	// création d’une session hibernate 
	// public static Session s = HibernateSession.getSession();
	
	private static Session s = HibernateSession.getSession();
	
//	public static Utilisateur getUtlisateur() {
//		return null;
//	}

}
