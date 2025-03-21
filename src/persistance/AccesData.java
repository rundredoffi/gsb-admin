package persistance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
	
	public static String getMotDePasseByNomUtilisateur(String login, String mdp) {
        Session session = null;
        String motDePasse = null;

        try {
            session = s.getSession();
            String hql = "SELECT mdp FROM Utilisateur WHERE login = :login";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("login", login);

            motDePasse = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return motDePasse;
    }
	
//	public static Utilisateur getUtlisateur() {
//		return null;
//	}

}
