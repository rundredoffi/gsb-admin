package persistance;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.StoredProcedureQuery;

import metier.FicheFrais;
import metier.Region;
import metier.Role;
import metier.Utilisateur;
import utils.Logger;



public class AccesData {

	// Constructeur privé pour empêcher l'instanciation de cette classe utilitaire
	private AccesData() {
		throw new IllegalStateException("Utility class");
	}

	// Constantes pour les paramètres des procédures stockées
	private static final String PARAM_MOIS = "mois";
	private static final String PARAM_REGION = "region";
	private static final String PARAM_P_MOIS = "p_mois";
	private static final String PARAM_P_REGION = "p_region";

	private static Session s = HibernateSession.getSession();

	public static List<Utilisateur> getLesUtilisateur() {
		Query<Utilisateur> query = s.createQuery("from Utilisateur", Utilisateur.class);
        return query.list();
	}
	
	public static List<String> getLesMois() {
	    Query<String> query = s.createQuery("select distinct ff.id.mois from FicheFrais ff", String.class);
	    return query.list();
	}
	
	
	public static List<Region> getLesRegions() {
		Query<Region> query = s.createQuery("from Region", Region.class);
		return query.list();
	}
	
	public static List<Role> getLesRoles() {
		Query<Role> query = s.createQuery("from Role", Role.class);
		return query.list();
	}
	
	public static Role getRoleById(String idRole) {
        Role role = null;
        try {
            String hql = "FROM Role r WHERE r.idRole = :idRole";
            Query<Role> query = s.createQuery(hql, Role.class);
            query.setParameter("idRole", idRole);
            role = query.uniqueResult();
        } catch (Exception e) {
            Logger.error("Erreur lors de la récupération du rôle avec ID " + idRole, e);
        }
        return role;
    }
	
	public static List<FicheFrais> getLesFicheFrais() {
		Query<FicheFrais> query = s.createQuery("from FicheFrais", FicheFrais.class);
		return query.list();
	}
	
	public static Utilisateur getUtilisateurByLoginAndMdp(String login, String motDePasse) {
	    Session session = null;
	    Utilisateur utilisateur = null;  
	    try {
	        session = HibernateSession.getSession();
	        String hql = "FROM Utilisateur u WHERE u.login = :login AND u.mdp = :motDePasse";
	        Query<Utilisateur> query = session.createQuery(hql, Utilisateur.class);
	        query.setParameter("login", login);
	        query.setParameter("motDePasse", motDePasse);  

	        utilisateur = query.uniqueResult();  
	    } catch (Exception e) {
	        Logger.error("Erreur lors de la récupération de l'utilisateur", e);
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
	
	public static Utilisateur getUtilisateurByID(String id) {
	    Session session = null;
	    Utilisateur utilisateur = null;  
	    try {
	        session = HibernateSession.getSession();
	        String hql = "FROM Utilisateur u WHERE u.id = :id";
	        Query<Utilisateur> query = session.createQuery(hql, Utilisateur.class);
	        query.setParameter("id", id);

	        utilisateur = query.uniqueResult();  
	    } catch (Exception e) {
	        Logger.error("Erreur lors de la récupération de l'utilisateur avec ID " + id, e);
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
			Logger.error("Erreur lors de l'insertion de l'utilisateur", e);
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
	        Logger.error("Erreur lors de la mise à jour de l'utilisateur", e);
	        if (t != null && t.isActive()) {
	            t.rollback();
	        }
	    }
	    return b;
	}
	
	public static Boolean deleteVisiteur(String utilisateurId) {
		Boolean success = false;

        try (Connection conn = AccesBD.getInstance();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM utilisateur WHERE idUtilisateur = ?")) {
            
            // Définir le paramètre
            pstmt.setString(1, utilisateurId);

            // Exécuter la déclaration
            int affectedRows = pstmt.executeUpdate();

            // Vérifier si des lignes ont été affectées
            if (affectedRows > 0) {
                success = true;
            }
        } catch (SQLException e) {
            // Récupérer et afficher le message d'erreur SQL
            Logger.error("Erreur lors de la suppression de l'utilisateur", e);
        }

        return success;
    }
	
	public static List<Object[]> getStatsFFMontRegion(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("get_stats_ffmont_region");
        query.registerStoredProcedureParameter(PARAM_MOIS, String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter(PARAM_REGION, Integer.class, javax.persistence.ParameterMode.IN);
        query.setParameter(PARAM_MOIS, month);
        query.setParameter(PARAM_REGION, region);
        query.execute();
        return query.getResultList();
    }

	public static List<Object[]> getStatsHFMontRegion(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("get_stats_hfmont_region");
        query.registerStoredProcedureParameter(PARAM_MOIS, String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter(PARAM_REGION, Integer.class, javax.persistence.ParameterMode.IN);
        query.setParameter(PARAM_MOIS, month);
        query.setParameter(PARAM_REGION, region);
        query.execute();
        return query.getResultList();
    }    public static List<Object[]> getStatsHfRegion(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("get_stats_hf_region");
        query.registerStoredProcedureParameter(PARAM_MOIS, String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter(PARAM_REGION, Integer.class, javax.persistence.ParameterMode.IN);
        query.setParameter(PARAM_MOIS, month);
        query.setParameter(PARAM_REGION, region);
        query.execute();
        return query.getResultList();
    }

    public static List<Object[]> getCombinedStats(String month, int region) {
        List<Object[]> statsFF = getStatsFFMontRegion(month, region);
        List<Object[]> statsHF = getStatsHFMontRegion(month, region);
        List<Object[]> statsHfRegion = getStatsHfRegion(month, region);

        List<Object[]> combinedStats = new ArrayList<>();
        for (Object[] ff : statsFF) {
            String idUtilisateur = (String) ff[0];
            double montantFraisHorsForfait = findMontantFraisHorsForfait(statsHF, idUtilisateur);
            int nbFraisHorsForfait = findNbFraisHorsForfait(statsHfRegion, idUtilisateur);

            Object[] combined = Arrays.copyOf(ff, ff.length + 2);
            combined[4] = montantFraisHorsForfait;
            combined[5] = nbFraisHorsForfait;
            combinedStats.add(combined);
        }

        return combinedStats;
    }

    private static double findMontantFraisHorsForfait(List<Object[]> statsHF, String idUtilisateur) {
        for (Object[] hf : statsHF) {
            if (hf[0].equals(idUtilisateur) && hf.length > 3) {
                return ((Number) hf[3]).doubleValue();
            }
        }
        return 0;
    }

    private static int findNbFraisHorsForfait(List<Object[]> statsHfRegion, String idUtilisateur) {
        for (Object[] hr : statsHfRegion) {
            if (hr[0].equals(idUtilisateur) && hr.length > 3) {
                return ((Number) hr[3]).intValue();
            }
        }
        return 0;
    }
    
    public static List<FicheFrais> retrieveFicheFraisByRegion(int regionId) {
        String hql = "SELECT ff FROM FicheFrais ff JOIN Utilisateur u ON ff.id.idVisiteur = u.idUtilisateur WHERE u.region.idRegion = :regionId";
        Query<FicheFrais> query = s.createQuery(hql, FicheFrais.class);
        query.setParameter("regionId", regionId);
        return query.list();
    }
    
    public static Object getMoyenneMontantFraisForfait(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("GetMoyenneMontantFraisForfait")
            .registerStoredProcedureParameter(PARAM_P_MOIS, String.class, javax.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter(PARAM_P_REGION, Integer.class, javax.persistence.ParameterMode.IN)
            .setParameter(PARAM_P_MOIS, month)
            .setParameter(PARAM_P_REGION, region);

        Logger.debug("Appel de la procédure avec mois : " + month + ", région : " + region);

        query.execute();

        List<Object> resultList = query.getResultList();
        Logger.debug("Résultats retournés par la procédure : " + resultList);

        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public static Object getMoyenneMontantFraisHorsForfait(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("GetMoyenneMontantFraisHorsForfait")
                .registerStoredProcedureParameter(PARAM_P_MOIS, String.class, javax.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter(PARAM_P_REGION, Integer.class, javax.persistence.ParameterMode.IN)
                .setParameter(PARAM_P_MOIS, month)
                .setParameter(PARAM_P_REGION, region);

        // Exécuter la requête et obtenir une liste de résultats
        query.execute();

        List<Object> resultList = query.getResultList(); // Utilisation de getResultList() au lieu de getSingleResult()
        Logger.debug("Moyenne frais forfait : " + resultList);

        // Vérifier si la liste de résultats est vide
        if (resultList.isEmpty()) {
            return null;  // Retourne null si aucun résultat
        }

        // Si des résultats sont trouvés, retourne le premier résultat
        return resultList.get(0); // Ou bien, adapte cette partie selon ce que tu attends comme résultat
    }

    public static List<Object[]> getCombinedMoyenneMontantFrais(String month, int region) {
        // Récupérer la moyenne des frais forfaits et des frais hors forfaits
        Object fraisForfait = getMoyenneMontantFraisForfait(month, region);
        Object fraisHorsForfait = getMoyenneMontantFraisHorsForfait(month, region);

        // Liste pour stocker les résultats combinés
        List<Object[]> combinedStats = new ArrayList<>();

        // Vérifier si les deux valeurs sont valides (non nulles)
        if (fraisForfait != null && fraisHorsForfait != null) {
            // Créer un tableau avec les deux valeurs combinées
            Object[] combined = new Object[2];
            combined[0] = fraisForfait; // Ajouter la moyenne des frais forfaits
            combined[1] = fraisHorsForfait; // Ajouter la moyenne des frais hors forfait
            

            // Ajouter les résultats combinés à la liste finale
            combinedStats.add(combined);
        }
        Logger.debug("Moyenne frais forfait : " + fraisForfait);
        Logger.debug("Moyenne frais hors forfait : " + fraisHorsForfait);
        return combinedStats;
    }}