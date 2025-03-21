package metier;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fichefrais")
public class FicheFrais {
    @EmbeddedId
    private FicheFraisId id;

    @Column(name = "nbJustificatifs")
    private int nbJustificatif;

    @Column(name = "montantValide")
    private int montantValide;

    @Column(name = "dateModif")
    private Date dateModif;

    @ManyToOne
    @JoinColumn(name = "idEtat")
    private Etat etat;

    public FicheFrais() {
    }

    public FicheFrais(FicheFraisId id, int nbJustificatif, int montantValide, Date dateModif, Etat etat) {
        this.id = id;
        this.nbJustificatif = nbJustificatif;
        this.montantValide = montantValide;
        this.dateModif = dateModif;
        this.etat = etat;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "FicheFrais [id=" + id + ", nbJustificatif=" + nbJustificatif + ", montantValide=" + montantValide
                + ", dateModif=" + dateModif + ", etat=" + etat + "]";
    }
}