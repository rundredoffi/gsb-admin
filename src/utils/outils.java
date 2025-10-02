package utils;

import java.util.ArrayList;
import java.util.List;

import persistance.AccesData;

public class Outils {
	
	// Constructeur privé pour empêcher l'instanciation de cette classe utilitaire
	private Outils() {
		throw new IllegalStateException("Utility class");
	}
	
	// Méthode utilisé pour récupérer les mois pour remplir les combobox des statistiques.
	public static String[] getMoisFormat() {
        List<String> moisList = AccesData.getLesMois();
        List<String> dateMois = new ArrayList<>();

        moisList.forEach(monMois->{
        	String year = monMois.substring(0, 4);
            String month = monMois.substring(4, 6);
            String formattedDate = month + "/" + year;
            dateMois.add(formattedDate);
        });
        return dateMois.toArray(new String[0]);
	}
	
	// Méthode utilisé pour renvoyer le moyen même structure que dans la BDD
	public static String formatageMoisSQL(String selectedMois) {
		//DateMoisSelected
        String month = selectedMois.substring(0, 2);
        String year = selectedMois.substring(3, 7);

        // Formater la date dans le format MM/YYYY
        return year+month ;
	}
}