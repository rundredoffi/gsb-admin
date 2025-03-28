package persistance;

import java.sql.*;

// classe permettant l'ouverture, la fermeture de la base 
public class AccesBD {
	// description des propriétés
	protected  static Connection con=null;
	private static String url;
	public static Connection  getInstance() {
		// accès direct sans source de données odbc
		url="jdbc:mysql://mysql-skylink.alwaysdata.net/skylink_gsb-admin?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";
		if (con==null)
		{
			try
			{// chargement du driver, librairie mysql connector liée au projet
				Class.forName("com.mysql.cj.jdbc.Driver");
				// connexion utilisateur root ou changer avec votre code utilisateur
				con=DriverManager.getConnection(url,"skylink_gsb","Skylink!GSB!1456");
			}
			// ouverture de la connexion
			catch (ClassNotFoundException e)
			{
				System.out.println(e.getMessage());
				System.out.println("échec driver");
			}
			catch (SQLException e)
			{
				System.out.println(e.getMessage());
				System.out.println("échec de connexion bd ");
			}
		}
		return con;
	}
	//	 fermeture de la connexion
	public static void close(){
		try { 
			con.close();
		}
		catch(Exception e) {e.printStackTrace();
		System.out.println("problème lors de la fermeture");}
	}
}



