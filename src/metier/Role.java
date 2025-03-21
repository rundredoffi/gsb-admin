package metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
    @Id
    @Column(name="idRole")
    private String idRole;

    @Column(name="libelleRole")
    private String libelleRole;

    public Role() {
        super();
    }

    public Role(String idRole, String libelleRole) {
        super();
        this.idRole = idRole;
        this.libelleRole = libelleRole;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getLibelleRole() {
        return libelleRole;
    }

    public void setLibelleRole(String libelleRole) {
        this.libelleRole = libelleRole;
    }

    @Override
    public String toString() {
        return "Role [idRole=" + idRole + ", libelleRole=" + libelleRole + "]";
    }
}