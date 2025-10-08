package metier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe Utilisateur
 */
class UtilisateurTest {
    
    private Role testRole;
    private Region testRegion;
    private Date testDate;
    
    @BeforeEach
    void setUp() {
        testRole = new Role("v", "Visiteur");
        testRegion = new Region(1, "Île-de-France");
        testDate = new Date();
    }
    
    @Test
    @DisplayName("Le constructeur par défaut doit créer un objet Utilisateur vide")
    void defaultConstructor_ShouldCreateEmptyUtilisateur() {
        // When
        Utilisateur utilisateur = new Utilisateur();
        
        // Then
        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getIdUtilisateur()).isNull();
        assertThat(utilisateur.getNom()).isNull();
        assertThat(utilisateur.getPrenom()).isNull();
        assertThat(utilisateur.getLogin()).isNull();
        assertThat(utilisateur.getMdp()).isNull();
        assertThat(utilisateur.getAdresse()).isNull();
        assertThat(utilisateur.getCp()).isNull();
        assertThat(utilisateur.getVille()).isNull();
        assertThat(utilisateur.getDateEmbauche()).isNull();
        assertThat(utilisateur.getEmail()).isNull();
        assertThat(utilisateur.getTelfixe()).isNull();
        assertThat(utilisateur.getTelPortable()).isNull();
        assertThat(utilisateur.getRegion()).isNull();
        assertThat(utilisateur.getRole()).isNull();
        assertThat(utilisateur.getLesFicheFrais()).isNull();
    }
    
    @Test
    @DisplayName("Le constructeur avec tous les paramètres doit créer un objet Utilisateur complet")
    void parameterizedConstructor_ShouldCreateCompleteUtilisateur() {
        // Given
        String idUtilisateur = "USR001";
        String nom = "Dupont";
        String prenom = "Jean";
        String login = "jdupont";
        String mdp = "password123";
        String adresse = "123 rue de la Paix";
        String cp = "75001";
        String ville = "Paris";
        String email = "jean.dupont@example.com";
        String telfixe = "0123456789";
        String telPortable = "0687654321";
        List<FicheFrais> lesFicheFrais = new ArrayList<>();
        
        // When
        Utilisateur utilisateur = new Utilisateur(idUtilisateur, nom, prenom, login, mdp, 
                                                 adresse, cp, ville, testDate, email, 
                                                 telfixe, telPortable, testRegion, testRole, 
                                                 lesFicheFrais);
        
        // Then
        assertThat(utilisateur.getIdUtilisateur()).isEqualTo(idUtilisateur);
        assertThat(utilisateur.getNom()).isEqualTo(nom);
        assertThat(utilisateur.getPrenom()).isEqualTo(prenom);
        assertThat(utilisateur.getLogin()).isEqualTo(login);
        assertThat(utilisateur.getMdp()).isEqualTo(mdp);
        assertThat(utilisateur.getAdresse()).isEqualTo(adresse);
        assertThat(utilisateur.getCp()).isEqualTo(cp);
        assertThat(utilisateur.getVille()).isEqualTo(ville);
        assertThat(utilisateur.getDateEmbauche()).isEqualTo(testDate);
        assertThat(utilisateur.getEmail()).isEqualTo(email);
        assertThat(utilisateur.getTelfixe()).isEqualTo(telfixe);
        assertThat(utilisateur.getTelPortable()).isEqualTo(telPortable);
        assertThat(utilisateur.getRegion()).isEqualTo(testRegion);
        assertThat(utilisateur.getRole()).isEqualTo(testRole);
        assertThat(utilisateur.getLesFicheFrais()).isEqualTo(lesFicheFrais);
    }
    
    @Test
    @DisplayName("Le constructeur avec paramètres null doit accepter les valeurs null")
    void parameterizedConstructor_WithNullValues_ShouldAcceptNullValues() {
        // When
        Utilisateur utilisateur = new Utilisateur(null, null, null, null, null, 
                                                 null, null, null, null, null, 
                                                 null, null, null, null, null);
        
        // Then
        assertThat(utilisateur.getIdUtilisateur()).isNull();
        assertThat(utilisateur.getNom()).isNull();
        assertThat(utilisateur.getPrenom()).isNull();
        assertThat(utilisateur.getLogin()).isNull();
        assertThat(utilisateur.getMdp()).isNull();
        assertThat(utilisateur.getAdresse()).isNull();
        assertThat(utilisateur.getCp()).isNull();
        assertThat(utilisateur.getVille()).isNull();
        assertThat(utilisateur.getDateEmbauche()).isNull();
        assertThat(utilisateur.getEmail()).isNull();
        assertThat(utilisateur.getTelfixe()).isNull();
        assertThat(utilisateur.getTelPortable()).isNull();
        assertThat(utilisateur.getRegion()).isNull();
        assertThat(utilisateur.getRole()).isNull();
        assertThat(utilisateur.getLesFicheFrais()).isNull();
    }
    
    @Test
    @DisplayName("setIdUtilisateur doit modifier l'id de l'utilisateur")
    void setIdUtilisateur_ShouldUpdateUserId() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newId = "USR999";
        
        // When
        utilisateur.setIdUtilisateur(newId);
        
        // Then
        assertThat(utilisateur.getIdUtilisateur()).isEqualTo(newId);
    }
    
    @Test
    @DisplayName("setNom doit modifier le nom de l'utilisateur")
    void setNom_ShouldUpdateUserNom() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newNom = "Martin";
        
        // When
        utilisateur.setNom(newNom);
        
        // Then
        assertThat(utilisateur.getNom()).isEqualTo(newNom);
    }
    
    @Test
    @DisplayName("setPrenom doit modifier le prénom de l'utilisateur")
    void setPrenom_ShouldUpdateUserPrenom() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newPrenom = "Marie";
        
        // When
        utilisateur.setPrenom(newPrenom);
        
        // Then
        assertThat(utilisateur.getPrenom()).isEqualTo(newPrenom);
    }
    
    @Test
    @DisplayName("setLogin doit modifier le login de l'utilisateur")
    void setLogin_ShouldUpdateUserLogin() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newLogin = "mmartin";
        
        // When
        utilisateur.setLogin(newLogin);
        
        // Then
        assertThat(utilisateur.getLogin()).isEqualTo(newLogin);
    }
    
    @Test
    @DisplayName("setMdp doit modifier le mot de passe de l'utilisateur")
    void setMdp_ShouldUpdateUserPassword() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newPassword = "newPassword456";
        
        // When
        utilisateur.setMdp(newPassword);
        
        // Then
        assertThat(utilisateur.getMdp()).isEqualTo(newPassword);
    }
    
    @Test
    @DisplayName("setAdresse doit modifier l'adresse de l'utilisateur")
    void setAdresse_ShouldUpdateUserAdresse() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newAdresse = "456 avenue des Champs";
        
        // When
        utilisateur.setAdresse(newAdresse);
        
        // Then
        assertThat(utilisateur.getAdresse()).isEqualTo(newAdresse);
    }
    
    @Test
    @DisplayName("setCp doit modifier le code postal de l'utilisateur")
    void setCp_ShouldUpdateUserCp() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newCp = "69000";
        
        // When
        utilisateur.setCp(newCp);
        
        // Then
        assertThat(utilisateur.getCp()).isEqualTo(newCp);
    }
    
    @Test
    @DisplayName("setVille doit modifier la ville de l'utilisateur")
    void setVille_ShouldUpdateUserVille() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newVille = "Lyon";
        
        // When
        utilisateur.setVille(newVille);
        
        // Then
        assertThat(utilisateur.getVille()).isEqualTo(newVille);
    }
    
    @Test
    @DisplayName("setDateEmbauche doit modifier la date d'embauche de l'utilisateur")
    void setDateEmbauche_ShouldUpdateUserDateEmbauche() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        Date newDate = new Date();
        
        // When
        utilisateur.setDateEmbauche(newDate);
        
        // Then
        assertThat(utilisateur.getDateEmbauche()).isEqualTo(newDate);
    }
    
    @Test
    @DisplayName("setEmail doit modifier l'email de l'utilisateur")
    void setEmail_ShouldUpdateUserEmail() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newEmail = "marie.martin@example.com";
        
        // When
        utilisateur.setEmail(newEmail);
        
        // Then
        assertThat(utilisateur.getEmail()).isEqualTo(newEmail);
    }
    
    @Test
    @DisplayName("setTelfixe doit modifier le téléphone fixe de l'utilisateur")
    void setTelfixe_ShouldUpdateUserTelfixe() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newTelfixe = "0472123456";
        
        // When
        utilisateur.setTelfixe(newTelfixe);
        
        // Then
        assertThat(utilisateur.getTelfixe()).isEqualTo(newTelfixe);
    }
    
    @Test
    @DisplayName("setTelPortable doit modifier le téléphone portable de l'utilisateur")
    void setTelPortable_ShouldUpdateUserTelPortable() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        String newTelPortable = "0612345678";
        
        // When
        utilisateur.setTelPortable(newTelPortable);
        
        // Then
        assertThat(utilisateur.getTelPortable()).isEqualTo(newTelPortable);
    }
    
    @Test
    @DisplayName("setRegion doit modifier la région de l'utilisateur")
    void setRegion_ShouldUpdateUserRegion() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        Region newRegion = new Region(2, "Rhône-Alpes");
        
        // When
        utilisateur.setRegion(newRegion);
        
        // Then
        assertThat(utilisateur.getRegion()).isEqualTo(newRegion);
    }
    
    @Test
    @DisplayName("setRole doit modifier le rôle de l'utilisateur")
    void setRole_ShouldUpdateUserRole() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        Role newRole = new Role("admin", "Administrateur");
        
        // When
        utilisateur.setRole(newRole);
        
        // Then
        assertThat(utilisateur.getRole()).isEqualTo(newRole);
    }
    
    @Test
    @DisplayName("setLesFicheFrais doit modifier la liste des fiches frais de l'utilisateur")
    void setLesFicheFrais_ShouldUpdateUserFicheFrais() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        List<FicheFrais> newFicheFrais = new ArrayList<>();
        
        // When
        utilisateur.setLesFicheFrais(newFicheFrais);
        
        // Then
        assertThat(utilisateur.getLesFicheFrais()).isEqualTo(newFicheFrais);
    }
    
    @Test
    @DisplayName("toString doit retourner une représentation string correcte")
    void toString_ShouldReturnCorrectStringRepresentation() {
        // Given
        Utilisateur utilisateur = new Utilisateur("USR001", "Dupont", "Jean", "jdupont", 
                                                  "password", "123 rue Test", "75001", "Paris", 
                                                  testDate, "jean@test.com", "0123456789", 
                                                  "0687654321", testRegion, testRole, new ArrayList<>());
        
        // When
        String result = utilisateur.toString();
        
        // Then
        assertThat(result).contains("Utilisateur");
        assertThat(result).contains("idUtilisateur=USR001");
        assertThat(result).contains("nom=Dupont");
        assertThat(result).contains("prenom=Jean");
        assertThat(result).contains("login=jdupont");
        assertThat(result).contains("email=jean@test.com");
    }
    
    @Test
    @DisplayName("toString avec des valeurs null doit fonctionner sans exception")
    void toString_WithNullValues_ShouldNotThrowException() {
        // Given
        Utilisateur utilisateur = new Utilisateur();
        
        // When & Then
        assertThatCode(() -> utilisateur.toString()).doesNotThrowAnyException();
        
        String result = utilisateur.toString();
        assertThat(result).contains("Utilisateur");
        assertThat(result).contains("null");
    }
    
    @Test
    @DisplayName("Les caractères spéciaux dans les champs doivent être gérés")
    void specialCharactersInFields_ShouldBeHandled() {
        // Given
        String nomWithSpecialChars = "Dupont-Moreau";
        String prenomWithAccents = "François";
        String emailWithSpecialChars = "françois.dupont-moreau@société.fr";
        
        // When
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nomWithSpecialChars);
        utilisateur.setPrenom(prenomWithAccents);
        utilisateur.setEmail(emailWithSpecialChars);
        
        // Then
        assertThat(utilisateur.getNom()).isEqualTo(nomWithSpecialChars);
        assertThat(utilisateur.getPrenom()).isEqualTo(prenomWithAccents);
        assertThat(utilisateur.getEmail()).isEqualTo(emailWithSpecialChars);
    }
    
    @Test
    @DisplayName("Les setters avec null doivent accepter les valeurs null")
    void settersWithNull_ShouldAcceptNull() {
        // Given
        Utilisateur utilisateur = new Utilisateur("USR001", "Dupont", "Jean", "jdupont", 
                                                  "password", "123 rue Test", "75001", "Paris", 
                                                  testDate, "jean@test.com", "0123456789", 
                                                  "0687654321", testRegion, testRole, new ArrayList<>());
        
        // When
        utilisateur.setNom(null);
        utilisateur.setPrenom(null);
        utilisateur.setEmail(null);
        utilisateur.setRegion(null);
        utilisateur.setRole(null);
        
        // Then
        assertThat(utilisateur.getNom()).isNull();
        assertThat(utilisateur.getPrenom()).isNull();
        assertThat(utilisateur.getEmail()).isNull();
        assertThat(utilisateur.getRegion()).isNull();
        assertThat(utilisateur.getRole()).isNull();
    }
}