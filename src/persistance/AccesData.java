package persistance;

import java.util.List;

import metier.Utilisateur;

import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccesData {

	// création d’une session hibernate 
	public static Session s = HibernateSession.getSession();
	
	public static Utilisateur getUtlisateur() {
		return null;
	}

}
