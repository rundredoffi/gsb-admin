package metier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe Region
 */
class RegionTest {
    
    @Test
    @DisplayName("Le constructeur par défaut doit créer un objet Region vide")
    void defaultConstructor_ShouldCreateEmptyRegion() {
        // When
        Region region = new Region();
        
        // Then
        assertThat(region).isNotNull();
        assertThat(region.getIdRegion()).isEqualTo(0);
        assertThat(region.getLibelleRegion()).isNull();
    }
    
    @Test
    @DisplayName("Le constructeur avec paramètres doit créer un objet Region avec les bonnes valeurs")
    void parameterizedConstructor_ShouldCreateRegionWithCorrectValues() {
        // Given
        int idRegion = 1;
        String libelleRegion = "Île-de-France";
        
        // When
        Region region = new Region(idRegion, libelleRegion);
        
        // Then
        assertThat(region.getIdRegion()).isEqualTo(idRegion);
        assertThat(region.getLibelleRegion()).isEqualTo(libelleRegion);
    }
    
    @Test
    @DisplayName("Le constructeur avec libellé null doit accepter la valeur null")
    void parameterizedConstructor_WithNullLibelle_ShouldAcceptNull() {
        // Given
        int idRegion = 2;
        
        // When
        Region region = new Region(idRegion, null);
        
        // Then
        assertThat(region.getIdRegion()).isEqualTo(idRegion);
        assertThat(region.getLibelleRegion()).isNull();
    }
    
    @Test
    @DisplayName("setIdRegion doit modifier l'id de la région")
    void setIdRegion_ShouldUpdateRegionId() {
        // Given
        Region region = new Region();
        int newIdRegion = 5;
        
        // When
        region.setIdRegion(newIdRegion);
        
        // Then
        assertThat(region.getIdRegion()).isEqualTo(newIdRegion);
    }
    
    @Test
    @DisplayName("setLibelleRegion doit modifier le libellé de la région")
    void setLibelleRegion_ShouldUpdateRegionLibelle() {
        // Given
        Region region = new Region();
        String newLibelleRegion = "Provence-Alpes-Côte d'Azur";
        
        // When
        region.setLibelleRegion(newLibelleRegion);
        
        // Then
        assertThat(region.getLibelleRegion()).isEqualTo(newLibelleRegion);
    }
    
    @Test
    @DisplayName("setIdRegion avec des valeurs négatives doit fonctionner")
    void setIdRegion_WithNegativeValue_ShouldWork() {
        // Given
        Region region = new Region();
        int negativeId = -1;
        
        // When
        region.setIdRegion(negativeId);
        
        // Then
        assertThat(region.getIdRegion()).isEqualTo(negativeId);
    }
    
    @Test
    @DisplayName("setLibelleRegion avec null doit accepter la valeur null")
    void setLibelleRegion_WithNull_ShouldAcceptNull() {
        // Given
        Region region = new Region(1, "Bretagne");
        
        // When
        region.setLibelleRegion(null);
        
        // Then
        assertThat(region.getLibelleRegion()).isNull();
    }
    
    @Test
    @DisplayName("toString doit retourner une représentation string correcte")
    void toString_ShouldReturnCorrectStringRepresentation() {
        // Given
        Region region = new Region(3, "Occitanie");
        
        // When
        String result = region.toString();
        
        // Then
        assertThat(result).contains("Region");
        assertThat(result).contains("idRegion=3");
        assertThat(result).contains("libelleRegion=Occitanie");
    }
    
    @Test
    @DisplayName("toString avec libellé null doit retourner une représentation string avec null")
    void toString_WithNullLibelle_ShouldReturnStringWithNull() {
        // Given
        Region region = new Region(4, null);
        
        // When
        String result = region.toString();
        
        // Then
        assertThat(result).contains("Region");
        assertThat(result).contains("idRegion=4");
        assertThat(result).contains("libelleRegion=null");
    }
    
    @Test
    @DisplayName("toString avec région par défaut doit retourner une représentation string correcte")
    void toString_WithDefaultRegion_ShouldReturnCorrectString() {
        // Given
        Region region = new Region();
        
        // When
        String result = region.toString();
        
        // Then
        assertThat(result).contains("Region");
        assertThat(result).contains("idRegion=0");
        assertThat(result).contains("libelleRegion=null");
    }
    
    @Test
    @DisplayName("Les getters et setters doivent être cohérents")
    void gettersAndSetters_ShouldBeConsistent() {
        // Given
        Region region = new Region();
        int idRegion = 7;
        String libelleRegion = "Hauts-de-France";
        
        // When
        region.setIdRegion(idRegion);
        region.setLibelleRegion(libelleRegion);
        
        // Then
        assertThat(region.getIdRegion()).isEqualTo(idRegion);
        assertThat(region.getLibelleRegion()).isEqualTo(libelleRegion);
    }
    
    @Test
    @DisplayName("Modifier les valeurs après construction doit fonctionner")
    void modifyValuesAfterConstruction_ShouldWork() {
        // Given
        Region region = new Region(1, "Région initiale");
        
        // When
        region.setIdRegion(99);
        region.setLibelleRegion("Région modifiée");
        
        // Then
        assertThat(region.getIdRegion()).isEqualTo(99);
        assertThat(region.getLibelleRegion()).isEqualTo("Région modifiée");
    }
    
    @Test
    @DisplayName("Les caractères spéciaux dans le libellé doivent être gérés")
    void specialCharactersInLibelle_ShouldBeHandled() {
        // Given
        String libelleWithSpecialChars = "Provence-Alpes-Côte d'Azur & Méditerranée éàèç";
        
        // When
        Region region = new Region(93, libelleWithSpecialChars);
        
        // Then
        assertThat(region.getLibelleRegion()).isEqualTo(libelleWithSpecialChars);
        assertThat(region.toString()).contains(libelleWithSpecialChars);
    }
    
    @Test
    @DisplayName("Les ID très grands doivent être gérés")
    void largeIds_ShouldBeHandled() {
        // Given
        int largeId = Integer.MAX_VALUE;
        
        // When
        Region region = new Region(largeId, "Grande région");
        
        // Then
        assertThat(region.getIdRegion()).isEqualTo(largeId);
    }
    
    @Test
    @DisplayName("Les libellés vides doivent être gérés")
    void emptyLibelle_ShouldBeHandled() {
        // Given
        String emptyLibelle = "";
        
        // When
        Region region = new Region(10, emptyLibelle);
        
        // Then
        assertThat(region.getLibelleRegion()).isEqualTo(emptyLibelle);
        assertThat(region.toString()).contains("libelleRegion=");
    }
    
    @Test
    @DisplayName("Les libellés très longs doivent être gérés")
    void veryLongLibelle_ShouldBeHandled() {
        // Given
        String longLibelle = "Une région avec un nom très très très long pour tester la gestion des chaînes de caractères importantes";
        
        // When
        Region region = new Region(11, longLibelle);
        
        // Then
        assertThat(region.getLibelleRegion()).isEqualTo(longLibelle);
        assertThat(region.toString()).contains(longLibelle);
    }
}