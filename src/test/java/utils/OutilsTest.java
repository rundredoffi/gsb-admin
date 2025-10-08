package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import persistance.AccesData;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe Outils
 */
@ExtendWith(MockitoExtension.class)
class OutilsTest {
    
    @Test
    @DisplayName("Le constructeur doit lever une exception IllegalStateException")
    void constructor_ShouldThrowIllegalStateException() {
        assertThatThrownBy(() -> {
            // Utilisation de la réflexion pour tester le constructeur privé
            java.lang.reflect.Constructor<Outils> constructor = Outils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        })
        .hasCauseInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Utility class");
    }
    
    @Test
    @DisplayName("getMoisFormat doit convertir correctement les mois du format base vers MM/yyyy")
    void getMoisFormat_ShouldConvertMonthsCorrectly() {
        // Given
        List<String> mockMoisList = Arrays.asList("202301", "202302", "202312", "202401");
        
        try (MockedStatic<AccesData> mockedAccesData = Mockito.mockStatic(AccesData.class)) {
            mockedAccesData.when(AccesData::getLesMois).thenReturn(mockMoisList);
            
            // When
            String[] result = Outils.getMoisFormat();
            
            // Then
            assertThat(result).hasSize(4);
            assertThat(result[0]).isEqualTo("01/2023");
            assertThat(result[1]).isEqualTo("02/2023");
            assertThat(result[2]).isEqualTo("12/2023");
            assertThat(result[3]).isEqualTo("01/2024");
        }
    }
    
    @Test
    @DisplayName("getMoisFormat doit retourner un tableau vide quand aucun mois n'est disponible")
    void getMoisFormat_WithEmptyList_ShouldReturnEmptyArray() {
        // Given
        List<String> emptyMoisList = Arrays.asList();
        
        try (MockedStatic<AccesData> mockedAccesData = Mockito.mockStatic(AccesData.class)) {
            mockedAccesData.when(AccesData::getLesMois).thenReturn(emptyMoisList);
            
            // When
            String[] result = Outils.getMoisFormat();
            
            // Then
            assertThat(result).isEmpty();
        }
    }
    
    @Test
    @DisplayName("getMoisFormat doit gérer correctement un seul mois")
    void getMoisFormat_WithSingleMonth_ShouldReturnSingleElement() {
        // Given
        List<String> singleMoisList = Arrays.asList("202306");
        
        try (MockedStatic<AccesData> mockedAccesData = Mockito.mockStatic(AccesData.class)) {
            mockedAccesData.when(AccesData::getLesMois).thenReturn(singleMoisList);
            
            // When
            String[] result = Outils.getMoisFormat();
            
            // Then
            assertThat(result).hasSize(1);
            assertThat(result[0]).isEqualTo("06/2023");
        }
    }
    
    @Test
    @DisplayName("formatageMoisSQL doit convertir du format MM/yyyy vers yyyyMM")
    void formatageMoisSQL_ShouldConvertCorrectly() {
        // Given & When & Then
        assertThat(Outils.formatageMoisSQL("01/2023")).isEqualTo("202301");
        assertThat(Outils.formatageMoisSQL("12/2023")).isEqualTo("202312");
        assertThat(Outils.formatageMoisSQL("06/2024")).isEqualTo("202406");
        assertThat(Outils.formatageMoisSQL("11/2022")).isEqualTo("202211");
    }
    
    @Test
    @DisplayName("formatageMoisSQL doit gérer correctement les mois avec un seul chiffre")
    void formatageMoisSQL_WithSingleDigitMonth_ShouldWork() {
        // Given & When & Then
        assertThat(Outils.formatageMoisSQL("01/2023")).isEqualTo("202301");
        assertThat(Outils.formatageMoisSQL("09/2023")).isEqualTo("202309");
    }
    
    @Test
    @DisplayName("formatageMoisSQL doit lever une exception pour un format invalide")
    void formatageMoisSQL_WithInvalidFormat_ShouldThrowException() {
        // Given & When & Then
        assertThatThrownBy(() -> Outils.formatageMoisSQL("invalid"))
            .isInstanceOf(StringIndexOutOfBoundsException.class);
        
        assertThatThrownBy(() -> Outils.formatageMoisSQL("1/2023"))
            .isInstanceOf(StringIndexOutOfBoundsException.class);
        
        assertThatThrownBy(() -> Outils.formatageMoisSQL("01/23"))
            .isInstanceOf(StringIndexOutOfBoundsException.class);
    }
    
    @Test
    @DisplayName("formatageMoisSQL avec entrée null doit lever une exception")
    void formatageMoisSQL_WithNullInput_ShouldThrowException() {
        // Given & When & Then
        assertThatThrownBy(() -> Outils.formatageMoisSQL(null))
            .isInstanceOf(NullPointerException.class);
    }
    
    @Test
    @DisplayName("formatageMoisSQL avec entrée vide doit lever une exception")
    void formatageMoisSQL_WithEmptyInput_ShouldThrowException() {
        // Given & When & Then
        assertThatThrownBy(() -> Outils.formatageMoisSQL(""))
            .isInstanceOf(StringIndexOutOfBoundsException.class);
    }
    
    @Test
    @DisplayName("getMoisFormat et formatageMoisSQL doivent être compatibles")
    void getMoisFormat_And_FormatageMoisSQL_ShouldBeCompatible() {
        // Given
        List<String> mockMoisList = Arrays.asList("202301", "202306", "202312");
        
        try (MockedStatic<AccesData> mockedAccesData = Mockito.mockStatic(AccesData.class)) {
            mockedAccesData.when(AccesData::getLesMois).thenReturn(mockMoisList);
            
            // When
            String[] formattedMois = Outils.getMoisFormat();
            
            // Then - Test de compatibilité bidirectionnelle
            for (int i = 0; i < formattedMois.length; i++) {
                String originalFormat = mockMoisList.get(i);
                String displayFormat = formattedMois[i];
                String backToOriginal = Outils.formatageMoisSQL(displayFormat);
                
                assertThat(backToOriginal).isEqualTo(originalFormat);
            }
        }
    }
    
    @Test
    @DisplayName("getMoisFormat doit gérer les années de différentes décennies")
    void getMoisFormat_WithDifferentDecades_ShouldWork() {
        // Given
        List<String> mockMoisList = Arrays.asList("199901", "200012", "201506", "202312", "203001");
        
        try (MockedStatic<AccesData> mockedAccesData = Mockito.mockStatic(AccesData.class)) {
            mockedAccesData.when(AccesData::getLesMois).thenReturn(mockMoisList);
            
            // When
            String[] result = Outils.getMoisFormat();
            
            // Then
            assertThat(result).hasSize(5);
            assertThat(result[0]).isEqualTo("01/1999");
            assertThat(result[1]).isEqualTo("12/2000");
            assertThat(result[2]).isEqualTo("06/2015");
            assertThat(result[3]).isEqualTo("12/2023");
            assertThat(result[4]).isEqualTo("01/2030");
        }
    }
}