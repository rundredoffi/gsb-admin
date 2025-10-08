package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe Logger
 */
class LoggerTest {
    
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private PrintStream originalOut;
    private PrintStream originalErr;
    
    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        originalOut = System.out;
        originalErr = System.err;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    @Test
    @DisplayName("Le constructeur doit lever une exception IllegalStateException")
    void constructor_ShouldThrowIllegalStateException() {
        assertThatThrownBy(() -> {
            // Utilisation de la réflexion pour tester le constructeur privé
            java.lang.reflect.Constructor<Logger> constructor = Logger.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        })
        .hasCauseInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Utility class");
    }
    
    @Test
    @DisplayName("Logger.info doit écrire un message avec le niveau INFO sur System.out")
    void info_ShouldWriteToStandardOut() {
        // Given
        String message = "Test info message";
        
        // When
        Logger.info(message);
        
        // Then
        String output = outContent.toString();
        assertThat(output)
            .contains("INFO")
            .contains(message)
            .contains(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertThat(errContent.toString()).isEmpty();
    }
    
    @Test
    @DisplayName("Logger.error doit écrire un message avec le niveau ERROR sur System.err")
    void error_ShouldWriteToStandardErr() {
        // Given
        String message = "Test error message";
        
        // When
        Logger.error(message);
        
        // Then
        String output = errContent.toString();
        assertThat(output)
            .contains("ERROR")
            .contains(message)
            .contains(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertThat(outContent.toString()).isEmpty();
    }
    
    @Test
    @DisplayName("Logger.error avec exception doit inclure le message de l'exception")
    void errorWithThrowable_ShouldIncludeThrowableMessage() {
        // Given
        String message = "Test error with exception";
        RuntimeException exception = new RuntimeException("Exception message");
        
        // When
        Logger.error(message, exception);
        
        // Then
        String output = errContent.toString();
        assertThat(output)
            .contains("ERROR")
            .contains(message)
            .contains("Exception message")
            .contains("-");
    }
    
    @Test
    @DisplayName("Logger.debug doit écrire un message avec le niveau DEBUG sur System.out")
    void debug_ShouldWriteToStandardOut() {
        // Given
        String message = "Test debug message";
        
        // When
        Logger.debug(message);
        
        // Then
        String output = outContent.toString();
        assertThat(output)
            .contains("DEBUG")
            .contains(message);
        assertThat(errContent.toString()).isEmpty();
    }
    
    @Test
    @DisplayName("Logger.warn doit écrire un message avec le niveau WARN sur System.out")
    void warn_ShouldWriteToStandardOut() {
        // Given
        String message = "Test warn message";
        
        // When
        Logger.warn(message);
        
        // Then
        String output = outContent.toString();
        assertThat(output)
            .contains("WARN")
            .contains(message);
        assertThat(errContent.toString()).isEmpty();
    }
    
    @Test
    @DisplayName("Le format des messages de log doit être correct")
    void logFormat_ShouldBeCorrect() {
        // Given
        String message = "Test message format";
        
        // When
        Logger.info(message);
        
        // Then
        String output = outContent.toString().trim();
        // Format attendu: [yyyy-MM-dd HH:mm:ss] LEVEL - message
        assertThat(output).matches("\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\] INFO - " + message);
    }
    
    @Test
    @DisplayName("Les messages avec caractères spéciaux doivent être correctement affichés")
    void logWithSpecialCharacters_ShouldBeHandledCorrectly() {
        // Given
        String messageWithSpecialChars = "Test message with éàç & symbols 123!@#";
        
        // When
        Logger.info(messageWithSpecialChars);
        
        // Then
        String output = outContent.toString();
        assertThat(output).contains(messageWithSpecialChars);
    }
    
    @Test
    @DisplayName("Les messages null doivent être gérés sans exception")
    void logWithNullMessage_ShouldNotThrowException() {
        // When/Then
        assertThatCode(() -> {
            Logger.info(null);
            Logger.error(null);
            Logger.debug(null);
            Logger.warn(null);
        }).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("Les messages vides doivent être correctement loggés")
    void logWithEmptyMessage_ShouldBeHandledCorrectly() {
        // Given
        String emptyMessage = "";
        
        // When
        Logger.info(emptyMessage);
        
        // Then
        String output = outContent.toString();
        assertThat(output).contains("INFO");
        // Le message vide doit toujours produire un log formaté
        assertThat(output).matches("\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\] INFO - ");
    }
}