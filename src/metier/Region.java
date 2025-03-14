package metier;

public class Region {
	int idRegion;
	String LibelleRegion;
	
	public Region(int idRegion, String libelleRegion) {
		super();
		this.idRegion = idRegion;
		LibelleRegion = libelleRegion;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getLibelleRegion() {
		return LibelleRegion;
	}

	public void setLibelleRegion(String libelleRegion) {
		LibelleRegion = libelleRegion;
	}

	@Override
	public String toString() {
		return "Region [idRegion=" + idRegion + ", LibelleRegion=" + LibelleRegion + "]";
	}
	
	
	
}
