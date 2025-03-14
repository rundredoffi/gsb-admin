package metier;

import java.util.Date;

public class FicheFrais {
	private String mois;
	private int nbJustificatif;
	private int montantValide;
	private Date dateModif;
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
