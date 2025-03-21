package metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Region")
public class Region {
    @Id
    @Column(name="idRegion")
    private int idRegion;

    @Column(name="libelleRegion")
    private String libelleRegion;

    public Region() {
        super();
    }

    public Region(int idRegion, String libelleRegion) {
        super();
        this.idRegion = idRegion;
        this.libelleRegion = libelleRegion;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getLibelleRegion() {
        return libelleRegion;
    }

    public void setLibelleRegion(String libelleRegion) {
        this.libelleRegion = libelleRegion;
    }

    @Override
    public String toString() {
        return "Region [idRegion=" + idRegion + ", libelleRegion=" + libelleRegion + "]";
    }
}