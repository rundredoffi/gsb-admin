package persistance;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import utils.Logger;

// classe permettant l'ouverture, la fermeture de la base 
public class AccesBD {
	// description des propriétés
	protected static Connection con = null;
	private static Properties dbProperties = null;
	
	// Constructeur privé pour empêcher l'instanciation de cette classe utilitaire
	private AccesBD() {
		throw new IllegalStateException("Utility class");
	}
	
	// Chargement des propriétés de base de données
	private static Properties getDbProperties() {
		if (dbProperties == null) {
			dbProperties = new Properties();
			try (InputStream input = AccesBD.class.getResourceAsStream("/resources/db.properties")) {
				if (input == null) {
					throw new IOException("Fichier de configuration db.properties introuvable");
				}
				dbProperties.load(input);
			} catch (IOException e) {
				Logger.error("Erreur lors du chargement de la configuration de base de données", e);
				// Valeurs par défaut ou gestion d'erreur
				throw new DatabaseConfigurationException("Configuration de base de données non disponible", e);
			}
		}
		return dbProperties;
	}
	
	public static Connection getInstance() {
		if (con == null) {
			try {
				Properties props = getDbProperties();
				String url = props.getProperty("db.url");
				String username = props.getProperty("db.username");
				String password = props.getProperty("db.password");
				String driver = props.getProperty("db.driver");
				
				// chargement du driver
				Class.forName(driver);
				// connexion avec les identifiants du fichier de configuration
				con = DriverManager.getConnection(url, username, password);
			}
			// ouverture de la connexion
			catch (ClassNotFoundException e) {
				Logger.error("Erreur lors du chargement du driver", e);
				throw new DatabaseConfigurationException("Driver de base de données non trouvé", e);
			}
			catch (SQLException e) {
				Logger.error("Erreur de connexion à la base de données", e);
				throw new DatabaseConfigurationException("Échec de connexion à la base de données", e);
			}
		}
		return con;
	}
	//	 fermeture de la connexion
	public static void close(){
		try { 
			if (con != null && !con.isClosed()) {
				con.close();
			}
		}
		catch(SQLException e) {
			Logger.error("Erreur lors de la fermeture de la connexion", e);
		}
	}
}



