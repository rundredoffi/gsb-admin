package metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Region")

public class Region {
	@Id
	@Column (name="idRegion")
	private int idRegion;
	@Column (name="libelleRegion")
	private String LibelleRegion;
	
	public Region() {
		super();
	}
	
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
