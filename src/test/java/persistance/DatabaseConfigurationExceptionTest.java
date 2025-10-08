package persistance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe DatabaseConfigurationException
 */
class DatabaseConfigurationExceptionTest {
    
    @Test
    @DisplayName("Le constructeur avec message doit créer une exception avec le bon message")
    void constructorWithMessage_ShouldCreateExceptionWithCorrectMessage() {
        // Given
        String message = "Erreur de configuration de base de données";
        
        // When
        DatabaseConfigurationException exception = new DatabaseConfigurationException(message);
        
        // Then
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }
    
    @Test
    @DisplayName("Le constructeur avec message et cause doit créer une exception complète")
    void constructorWithMessageAndCause_ShouldCreateCompleteException() {
        // Given
        String message = "Erreur de configuration de base de données";
        Exception cause = new RuntimeException("Cause originale");
        
        // When
        DatabaseConfigurationException exception = new DatabaseConfigurationException(message, cause);
        
        // Then
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }
    
    @Test
    @DisplayName("Le constructeur avec message null doit accepter null")
    void constructorWithNullMessage_ShouldAcceptNull() {
        // When
        DatabaseConfigurationException exception = new DatabaseConfigurationException(null);
        
        // Then
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }
    
    @Test
    @DisplayName("Le constructeur avec cause null doit accepter null")
    void constructorWithNullCause_ShouldAcceptNull() {
        // Given
        String message = "Message d'erreur";
        
        // When
        DatabaseConfigurationException exception = new DatabaseConfigurationException(message, null);
        
        // Then
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }
    
    @Test
    @DisplayName("L'exception doit être une RuntimeException")
    void exception_ShouldBeRuntimeException() {
        // When
        DatabaseConfigurationException exception = new DatabaseConfigurationException("Test");
        
        // Then
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
    
    @Test
    @DisplayName("L'exception doit pouvoir être lancée")
    void exception_ShouldBeThrowable() {
        // Given
        String message = "Erreur de test";
        
        // When/Then
        assertThatThrownBy(() -> {
            throw new DatabaseConfigurationException(message);
        })
        .isInstanceOf(DatabaseConfigurationException.class)
        .hasMessage(message);
    }
    
    @Test
    @DisplayName("L'exception avec cause doit pouvoir être lancée")
    void exceptionWithCause_ShouldBeThrowable() {
        // Given
        String message = "Erreur de test";
        Exception cause = new IllegalArgumentException("Argument invalide");
        
        // When/Then
        assertThatThrownBy(() -> {
            throw new DatabaseConfigurationException(message, cause);
        })
        .isInstanceOf(DatabaseConfigurationException.class)
        .hasMessage(message)
        .hasCause(cause);
    }
    
    @Test
    @DisplayName("L'exception doit préserver la stack trace")
    void exception_ShouldPreserveStackTrace() {
        // Given
        String message = "Erreur de test";
        
        // When
        DatabaseConfigurationException exception = new DatabaseConfigurationException(message);
        
        // Then
        assertThat(exception.getStackTrace()).isNotEmpty();
        assertThat(exception.getStackTrace()[0].getClassName()).contains("DatabaseConfigurationExceptionTest");
    }
    
    @Test
    @DisplayName("Plusieurs exceptions peuvent être créées indépendamment")
    void multipleExceptions_ShouldBeIndependent() {
        // Given
        String message1 = "Erreur 1";
        String message2 = "Erreur 2";
        Exception cause = new RuntimeException("Cause");
        
        // When
        DatabaseConfigurationException exception1 = new DatabaseConfigurationException(message1);
        DatabaseConfigurationException exception2 = new DatabaseConfigurationException(message2, cause);
        
        // Then
        assertThat(exception1.getMessage()).isEqualTo(message1);
        assertThat(exception1.getCause()).isNull();
        assertThat(exception2.getMessage()).isEqualTo(message2);
        assertThat(exception2.getCause()).isEqualTo(cause);
    }
    
    @Test
    @DisplayName("L'exception avec message vide doit fonctionner")
    void exceptionWithEmptyMessage_ShouldWork() {
        // Given
        String emptyMessage = "";
        
        // When
        DatabaseConfigurationException exception = new DatabaseConfigurationException(emptyMessage);
        
        // Then
        assertThat(exception.getMessage()).isEqualTo(emptyMessage);
    }
    
    @Test
    @DisplayName("L'exception avec message très long doit fonctionner")
    void exceptionWithVeryLongMessage_ShouldWork() {
        // Given
        String longMessage = "Une erreur très très très longue pour tester la gestion des messages de longueur importante dans les exceptions de configuration de base de données";
        
        // When
        DatabaseConfigurationException exception = new DatabaseConfigurationException(longMessage);
        
        // Then
        assertThat(exception.getMessage()).isEqualTo(longMessage);
    }
}