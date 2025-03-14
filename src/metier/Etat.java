package metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="etat")

public class Etat {
	@Id
	@Column (name="idEtat")
	private int idEtat;
	@Column (name="libelleEtat")
	private String LibelleEtat;
	
	public Etat() {
		super();
	}
	
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