package metier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe Role
 */
class RoleTest {
    
    @Test
    @DisplayName("Le constructeur par défaut doit créer un objet Role vide")
    void defaultConstructor_ShouldCreateEmptyRole() {
        // When
        Role role = new Role();
        
        // Then
        assertThat(role).isNotNull();
        assertThat(role.getIdRole()).isNull();
        assertThat(role.getLibelleRole()).isNull();
    }
    
    @Test
    @DisplayName("Le constructeur avec paramètres doit créer un objet Role avec les bonnes valeurs")
    void parameterizedConstructor_ShouldCreateRoleWithCorrectValues() {
        // Given
        String idRole = "admin";
        String libelleRole = "Administrateur";
        
        // When
        Role role = new Role(idRole, libelleRole);
        
        // Then
        assertThat(role.getIdRole()).isEqualTo(idRole);
        assertThat(role.getLibelleRole()).isEqualTo(libelleRole);
    }
    
    @Test
    @DisplayName("Le constructeur avec paramètres null doit accepter les valeurs null")
    void parameterizedConstructor_WithNullValues_ShouldAcceptNullValues() {
        // When
        Role role = new Role(null, null);
        
        // Then
        assertThat(role.getIdRole()).isNull();
        assertThat(role.getLibelleRole()).isNull();
    }
    
    @Test
    @DisplayName("setIdRole doit modifier l'id du rôle")
    void setIdRole_ShouldUpdateRoleId() {
        // Given
        Role role = new Role();
        String newIdRole = "visiteur";
        
        // When
        role.setIdRole(newIdRole);
        
        // Then
        assertThat(role.getIdRole()).isEqualTo(newIdRole);
    }
    
    @Test
    @DisplayName("setLibelleRole doit modifier le libellé du rôle")
    void setLibelleRole_ShouldUpdateRoleLibelle() {
        // Given
        Role role = new Role();
        String newLibelleRole = "Visiteur médical";
        
        // When
        role.setLibelleRole(newLibelleRole);
        
        // Then
        assertThat(role.getLibelleRole()).isEqualTo(newLibelleRole);
    }
    
    @Test
    @DisplayName("setIdRole avec null doit accepter la valeur null")
    void setIdRole_WithNull_ShouldAcceptNull() {
        // Given
        Role role = new Role("admin", "Administrateur");
        
        // When
        role.setIdRole(null);
        
        // Then
        assertThat(role.getIdRole()).isNull();
    }
    
    @Test
    @DisplayName("setLibelleRole avec null doit accepter la valeur null")
    void setLibelleRole_WithNull_ShouldAcceptNull() {
        // Given
        Role role = new Role("admin", "Administrateur");
        
        // When
        role.setLibelleRole(null);
        
        // Then
        assertThat(role.getLibelleRole()).isNull();
    }
    
    @Test
    @DisplayName("toString doit retourner une représentation string correcte")
    void toString_ShouldReturnCorrectStringRepresentation() {
        // Given
        Role role = new Role("v", "Visiteur");
        
        // When
        String result = role.toString();
        
        // Then
        assertThat(result).contains("Role");
        assertThat(result).contains("idRole=v");
        assertThat(result).contains("libelleRole=Visiteur");
    }
    
    @Test
    @DisplayName("toString avec des valeurs null doit retourner une représentation string avec null")
    void toString_WithNullValues_ShouldReturnStringWithNull() {
        // Given
        Role role = new Role(null, null);
        
        // When
        String result = role.toString();
        
        // Then
        assertThat(result).contains("Role");
        assertThat(result).contains("idRole=null");
        assertThat(result).contains("libelleRole=null");
    }
    
    @Test
    @DisplayName("toString avec rôle par défaut doit retourner une représentation string avec null")
    void toString_WithDefaultRole_ShouldReturnStringWithNull() {
        // Given
        Role role = new Role();
        
        // When
        String result = role.toString();
        
        // Then
        assertThat(result).contains("Role");
        assertThat(result).contains("idRole=null");
        assertThat(result).contains("libelleRole=null");
    }
    
    @Test
    @DisplayName("Les getters et setters doivent être cohérents")
    void gettersAndSetters_ShouldBeConsistent() {
        // Given
        Role role = new Role();
        String idRole = "manager";
        String libelleRole = "Gestionnaire";
        
        // When
        role.setIdRole(idRole);
        role.setLibelleRole(libelleRole);
        
        // Then
        assertThat(role.getIdRole()).isEqualTo(idRole);
        assertThat(role.getLibelleRole()).isEqualTo(libelleRole);
    }
    
    @Test
    @DisplayName("Modifier les valeurs après construction doit fonctionner")
    void modifyValuesAfterConstruction_ShouldWork() {
        // Given
        Role role = new Role("initial", "Initial Role");
        
        // When
        role.setIdRole("modified");
        role.setLibelleRole("Modified Role");
        
        // Then
        assertThat(role.getIdRole()).isEqualTo("modified");
        assertThat(role.getLibelleRole()).isEqualTo("Modified Role");
    }
    
    @Test
    @DisplayName("Les caractères spéciaux dans les valeurs doivent être gérés")
    void specialCharactersInValues_ShouldBeHandled() {
        // Given
        String idWithSpecialChars = "rôle-éàç_123";
        String libelleWithSpecialChars = "Rôle avec accents éàèç et symboles !@#";
        
        // When
        Role role = new Role(idWithSpecialChars, libelleWithSpecialChars);
        
        // Then
        assertThat(role.getIdRole()).isEqualTo(idWithSpecialChars);
        assertThat(role.getLibelleRole()).isEqualTo(libelleWithSpecialChars);
        assertThat(role.toString()).contains(idWithSpecialChars);
        assertThat(role.toString()).contains(libelleWithSpecialChars);
    }
}