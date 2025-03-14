package metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")

public class Role {
	@Id
	@Column (name="idRole")
	private int idRole;
	@Column (name="libelleRole")
	private String LibelleRole;
		
	public Role() {
		super();
	}

	public Role(int idRole, String libelleRole) {
		super();
		this.idRole = idRole;
		LibelleRole = libelleRole;
	}
	
	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getLibelleRole() {
		return LibelleRole;
	}

	public void setLibelleRole(String libelleRole) {
		LibelleRole = libelleRole;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", LibelleRole=" + LibelleRole + "]";
	}
	
}
