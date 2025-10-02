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
	private String idEtat;
	@Column (name="libelleEtat")
	private String libelleEtat;
	
	public Etat() {
		super();
	}

	public Etat(String idEtat, String libelleEtat) {
		super();
		this.idEtat = idEtat;
		this.libelleEtat = libelleEtat;
	}
	
	public String getIdEtat() {
		return idEtat;
	}

	public void setIdEtat(String idEtat) {
		this.idEtat = idEtat;
	}

	public String getLibelleEtat() {
		return libelleEtat;
	}

	public void setLibelleEtat(String libelleEtat) {
		this.libelleEtat = libelleEtat;
	}

	@Override
	public String toString() {
		return "Etat [idEtat=" + idEtat + ", libelleEtat=" + libelleEtat + "]";
	}

}