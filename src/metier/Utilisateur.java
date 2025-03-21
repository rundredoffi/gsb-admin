package metier;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur")
public class Utilisateur {
    @Id
    @Column(name="idUtilisateur")
    private String idUtilisateur;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="login")
    private String login;

    @Column(name="mdp")
    private String mdp;

    @Column(name="adresse")
    private String adresse;

    @Column(name="cp")
    private String cp;

    @Column(name="ville")
    private String ville;

    @Column(name="dateEmbauche")
    private Date dateEmbauche;

    @Column(name="email")
    private String email;

    @Column(name="telFixe")
    private String telfixe;

    @Column(name="telPortable")
    private String telPortable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRegion")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole")
    private Role role;

    @OneToMany(mappedBy = "id.idVisiteur")
    private List<FicheFrais> lesFicheFrais;

    public Utilisateur() {
        super();
    }

    public Utilisateur(String idUtilisateur, String nom, String prenom, String login, String mdp, String adresse, String cp, String ville,
                       Date dateEmbauche, String email, String telfixe, String telPortable, Region region, Role role,
                       List<FicheFrais> lesFicheFrais) {
        super();
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.dateEmbauche = dateEmbauche;
        this.email = email;
        this.telfixe = telfixe;
        this.telPortable = telPortable;
        this.region = region;
        this.role = role;
        this.lesFicheFrais = lesFicheFrais;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", mdp=" + mdp + ", adresse=" + adresse + ", cp="
                + cp + ", ville=" + ville + ", dateEmbauche=" + dateEmbauche + ", email=" + email + ", telfixe="
                + telfixe + ", telPortable=" + telPortable + ", region=" + region + ", role=" + role
                + ", lesFicheFrais=" + lesFicheFrais + "]";
    }
}