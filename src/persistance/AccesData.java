package persistance;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import metier.FicheFrais;
import metier.Region;
import metier.Utilisateur;



public class AccesData {

private static Session s = HibernateSession.getSession();

	public static List<Utilisateur> getLesUtilisateur() {
		Query<Utilisateur> query = s.createQuery("from Utilisateur", Utilisateur.class);
        return query.list();
	}
	
	public static List<Region> getLesRegions() {
		Query<Region> query = s.createQuery("from Region", Region.class);
		return query.list();
	}
	

	
	
	public static List<FicheFrais> getLesFicheFrais() {
		Query<FicheFrais> query = s.createQuery("from FicheFrais", FicheFrais.class);
		return query.list();
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
		Query<Utilisateur> query = s.createQuery("from Utilisateur where idRole='v'", Utilisateur.class);
		return query.list();
	}
	
	public static List<FicheFrais> getLesFichesFrais() {
		Query<FicheFrais> query = s.createQuery("from FicheFrais", FicheFrais.class);
		return query.list();
	}
	
	public static Utilisateur getUtilisateurByID(String id) {
	    Session session = null;
	    Utilisateur utilisateur = null;  
	    try {
	        String hql = "FROM Utilisateur u WHERE u.id = :id";
	        Query<Utilisateur> query = s.createQuery(hql, Utilisateur.class);
	        query.setParameter("id", id);

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
	public static Boolean insertionVisiteur(Utilisateur util) {
		Transaction t = s.beginTransaction();
		Boolean b = false;
		try {
			s.save(util);
			t.commit();
			b = true;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if (t.isActive()) {
				t.rollback();
			}
		}
		return b;
	}
	
	public static Boolean updateVisiteur(Utilisateur util) {
	    Transaction t = s.beginTransaction();
	    Boolean b = false;
	    try {
	        s.merge(util); // Merge permet de mettre à jour un objet détaché
	        t.commit();
	        b = true;
	    } catch (HibernateException e) {
	        System.out.println(e.getMessage());
	        if (t != null && t.isActive()) {
	            t.rollback();
	        }
	    }
	    return b;
	}
	
	public static Boolean deleteVisiteur(String utilisateurId) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        Boolean success = false;

        try {
            // Obtenir la connexion à partir de AccesBD
            conn = AccesBD.getInstance();

            // Créer la déclaration de suppression
            String sql = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, utilisateurId);

            // Exécuter la déclaration
            int affectedRows = pstmt.executeUpdate();

            // Vérifier si des lignes ont été affectées
            if (affectedRows > 0) {
                success = true;
            }
        } catch (SQLException e) {
            // Récupérer et afficher le message d'erreur SQL
            System.out.println(e.getMessage());
        } finally {
            // Fermer les ressources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) AccesBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
}
	
	