package persistance;

import java.util.TimeZone;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import utils.Logger;

public class HibernateSession {
	
	// Constructeur privé pour empêcher l'instanciation de cette classe utilitaire
	private HibernateSession() {
		throw new IllegalStateException("Utility class");
	}
	
	private static final SessionFactory sessionFactory;
	
	static {
        try {
            // Build a SessionFactory object from session-factory config
            // defined in the hibernate.cfg.xml file. In this file we
            // register the JDBC connection information, connection pool,
            // the hibernate dialect that we used and the mapping to our
            // hbm.xml file for each pojo (plain old java object).
            Configuration config = new Configuration();
            sessionFactory = config.configure().buildSessionFactory();
        } catch (Exception e) {
            Logger.error("Error in creating SessionFactory object: " + e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

	// Renvoie une session Hibernate
	public static Session getSession() throws HibernateException {
		sessionFactory.withOptions().jdbcTimeZone(TimeZone.getTimeZone("UTC"));
		return sessionFactory.openSession();
	}

	// Ferme la session Hibernate
	public static void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	
	// Ferme la SessionFactory
	public static void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}