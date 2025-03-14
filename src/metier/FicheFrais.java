package metier;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="fichefrais")
public class FicheFrais {
	@EmbeddedId
	@Column(name="mois")
	private String mois;
	@EmbeddedId
	@Column(name="idVisiteur")
	private String idVisiteur;
	@Column(name="nbJustificatifs")
	private int nbJustificatif;
	@Column(name="montantValide")
	private int montantValide;
    @Temporal(TemporalType.DATE)
	@Column(name="dateModif")
	private Date dateModif;
    @Enumerated(EnumType.STRING)
	private Etat etat;
	
	public FicheFrais(String mois, int nbJustificatif, int montantValide, Date dateModif, Etat etat,
			String idVisiteur) {
		super();
		this.mois = mois;
		this.nbJustificatif = nbJustificatif;
		this.montantValide = montantValide;
		this.dateModif = dateModif;
		this.etat = etat;
		this.idVisiteur = idVisiteur;
	}
	public FicheFrais() {
		super();
	}
	public String getIdVisiteur() {
		return idVisiteur;
	}
	public void setIdVisiteur(String idVisiteur) {
		this.idVisiteur = idVisiteur;
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
