package persistance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

import org.hibernate.Session;

import metier.FicheFrais;
import metier.Region;
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
	
	public static List<Region> getLesRegions() {
		return s.createQuery("from Region").list();
	}
	
	public static List<FicheFrais> getLesFicheFrais() {
		return s.createQuery("from fichefrais").list();
	}
	
	
	public static Utilisateur getUtilisateurByLoginAndMdp(String login, String motDePasse) {
	    Session session = null;
	    Utilisateur utilisateur = null;  
	    try {
	        String hql = "FROM Utilisateur u WHERE u.login = :login AND u.mdp = :motDePasse";
	        Query<Utilisateur> query = s.createQuery(hql, Utilisateur.class);
	        query.setParameter("login", login);
	        query.setParameter("motDePasse", motDePasse);  

	        utilisateur = query.uniqueResult();  
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();  
	        }
	    }

	    return utilisateur;  
	}
	
	public static List<Utilisateur> getLesVisiteurs() {
		return s.createQuery("from Utilisateur where idRole='v'").list();
	}
	
	public static List<FicheFrais> getLesFichesFrais() {
		return s.createQuery("from FicheFrais").list();
	}
}
	
	