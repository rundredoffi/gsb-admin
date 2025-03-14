package persistance;

import java.util.List;

import org.hibernate.Session;

import metier.Utilisateur;

import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccesData {

	
	private static Session s = HibernateSession.getSession();
	
	public static List<Utilisateur> getLesUtilisateur() {
		return s.createQuery("from Utilisateur").list();
	}
	
//	public static Utilisateur getUtlisateur() {
//		return null;
//	}

}
