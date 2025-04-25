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



public class AccesData {

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
            e.printStackTrace();
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
        }           
        

        return success;
    }
	
	public static List<Object[]> getStatsFFMontRegion(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("get_stats_ffmont_region");
        query.registerStoredProcedureParameter("mois", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("region", Integer.class, javax.persistence.ParameterMode.IN);
        query.setParameter("mois", month);
        query.setParameter("region", region);
        query.execute();
        return query.getResultList();
    }

    public static List<Object[]> getStatsHFMontRegion(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("get_stats_hfmont_region");
        query.registerStoredProcedureParameter("mois", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("region", Integer.class, javax.persistence.ParameterMode.IN);
        query.setParameter("mois", month);
        query.setParameter("region", region);
        query.execute();
        return query.getResultList();
    }

    public static List<Object[]> getStatsHfRegion(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("get_stats_hf_region");
        query.registerStoredProcedureParameter("mois", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("region", Integer.class, javax.persistence.ParameterMode.IN);
        query.setParameter("mois", month);
        query.setParameter("region", region);
        query.execute();
        return query.getResultList();
    }

    public static List<Object[]> getCombinedStats(String month, int region) {
        List<Object[]> statsFF = getStatsFFMontRegion(month, region);
        List<Object[]> statsHF = getStatsHFMontRegion(month, region);
        List<Object[]> statsHfRegion = getStatsHfRegion(month, region);

       

        // Combine the results into a single list
        List<Object[]> combinedStats = new ArrayList<>();
        for (Object[] ff : statsFF) {
            String idUtilisateur = (String) ff[0];
            boolean foundHF = false;
            boolean foundHfRegion = false;
            double montantFraisHorsForfait = 0;
            int nbFraisHorsForfait = 0;

            for (Object[] hf : statsHF) {
                if (hf[0].equals(idUtilisateur)) {
                    if (hf.length > 3) {
                        montantFraisHorsForfait = ((Number) hf[3]).doubleValue();
                    }
                    foundHF = true;
                    break;
                }
            }
            for (Object[] hr : statsHfRegion) {
                if (hr[0].equals(idUtilisateur)) {
                    if (hr.length > 3) {
                        nbFraisHorsForfait = ((Number) hr[3]).intValue();
                    }
                    foundHfRegion = true;
                    break;
                }
            }

            Object[] combined = Arrays.copyOf(ff, ff.length + 2);
            combined[4] = montantFraisHorsForfait;
            combined[5] = nbFraisHorsForfait;
            combinedStats.add(combined);
        }

        return combinedStats;
    }
    
    public static List<FicheFrais> retrieveFicheFraisByRegion(int regionId) {
        String hql = "SELECT ff FROM FicheFrais ff JOIN Utilisateur u ON ff.id.idVisiteur = u.idUtilisateur WHERE u.region.idRegion = :regionId";
        Query<FicheFrais> query = s.createQuery(hql, FicheFrais.class);
        query.setParameter("regionId", regionId);
        return query.list();
    }
    
    public static Object getMoyenneMontantFraisForfait(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("GetMoyenneMontantFraisForfait")
            .registerStoredProcedureParameter("p_mois", String.class, javax.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("p_region", Integer.class, javax.persistence.ParameterMode.IN)
            .setParameter("p_mois", month)
            .setParameter("p_region", region);

        System.out.printf("Appel de la procédure avec mois : %s, région : %d%n", month, region);

        query.execute();

        List<Object> resultList = query.getResultList();
        System.out.println("Résultats retournés par la procédure : " + resultList);

        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public static Object getMoyenneMontantFraisHorsForfait(String month, int region) {
        StoredProcedureQuery query = s.createStoredProcedureQuery("GetMoyenneMontantFraisHorsForfait")
                .registerStoredProcedureParameter("p_mois", String.class, javax.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_region", Integer.class, javax.persistence.ParameterMode.IN)
                .setParameter("p_mois", month)
                .setParameter("p_region", region);

        // Exécuter la requête et obtenir une liste de résultats
        query.execute();

        List<Object> resultList = query.getResultList(); // Utilisation de getResultList() au lieu de getSingleResult()
        System.out.println("Moyenne frais forfait : " + resultList);

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
        System.out.println("Moyenne frais forfait : " + fraisForfait);
        System.out.println("Moyenne frais hors forfait : " + fraisHorsForfait);
        return combinedStats;
    }}