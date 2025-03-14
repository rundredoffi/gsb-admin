package metier;

public class Role {
	int idRole;
	String Libelle;
		
	
	public Role(int idRole, String libelle) {
		super();
		this.idRole = idRole;
		Libelle = libelle;
	}

	

	public int getIdRole() {
		return idRole;
	}


	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}


	public String getLibelle() {
		return Libelle;
	}


	public void setLibelle(String libelle) {
		Libelle = libelle;
	}


	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", Libelle=" + Libelle + "]";
	}
}
