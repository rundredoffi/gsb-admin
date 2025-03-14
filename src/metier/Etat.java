package metier;

public class Etat {
	private int idEtat;
	private String LibelleEtat;
	
	public Etat(int idEtat, String libelleEtat) {
		super();
		this.idEtat = idEtat;
		LibelleEtat = libelleEtat;
	}
	
	
	

	public int getIdEtat() {
		return idEtat;
	}

	public void setIdEtat(int idEtat) {
		this.idEtat = idEtat;
	}

	public String getLibelleEtat() {
		return LibelleEtat;
	}

	public void setLibelleEtat(String libelleEtat) {
		LibelleEtat = libelleEtat;
	}

	@Override
	public String toString() {
		return "Etat [idEtat=" + idEtat + ", LibelleEtat=" + LibelleEtat + "]";
	}
	
	
	
	

}
