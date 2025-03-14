package metier;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="fichefrais")
public class FicheFrais {
	@Column(name="mois")
	private String mois;
	@Column(name="nbJustificatifs")
	private int nbJustificatif;
	@Column(name="montantValide")
	private int montantValide;
	@Column(name="dateModif")
	private Date dateModif;
	@ManyToOne
	@JoinColumn(name = "idEtat")
	private Etat etat;
		
	public FicheFrais(String mois, int nbJustificatif, int montantValide, Date dateModif, Etat etat) {
		super();
		this.mois = mois;
		this.nbJustificatif = nbJustificatif;
		this.montantValide = montantValide;
		this.dateModif = dateModif;
		this.etat = etat;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public int getNbJustificatif() {
		return nbJustificatif;
	}

	public void setNbJustificatif(int nbJustificatif) {
		this.nbJustificatif = nbJustificatif;
	}

	public int getMontantValide() {
		return montantValide;
	}

	public void setMontantValide(int montantValide) {
		this.montantValide = montantValide;
	}

	public Date getDateModif() {
		return dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "FicheFrais [mois=" + mois + ", nbJustificatif=" + nbJustificatif + ", montantValide=" + montantValide
				+ ", dateModif=" + dateModif + ", etat=" + etat + "]";
	}
	
	
	
}
