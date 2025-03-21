package metier;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FicheFraisId implements Serializable {
    @Column(name = "idVisiteur")
    private String idVisiteur;

    @Column(name = "mois")
    private String mois;

    public FicheFraisId() {
    }

    public FicheFraisId(String idVisiteur, String mois) {
        this.idVisiteur = idVisiteur;
        this.mois = mois;
    }

    // Getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FicheFraisId that = (FicheFraisId) o;

        if (!idVisiteur.equals(that.idVisiteur)) return false;
        return mois.equals(that.mois);
    }

    @Override
    public int hashCode() {
        int result = idVisiteur.hashCode();
        result = 31 * result + mois.hashCode();
        return result;
    }
}