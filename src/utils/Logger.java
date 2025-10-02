package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger centralisé pour l'application GSB-Admin
 * Fournit des méthodes de logging avec différents niveaux de gravité
 */
public class Logger {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Constantes pour les niveaux de log
    private static final String LEVEL_INFO = "INFO";
    private static final String LEVEL_ERROR = "ERROR";
    private static final String LEVEL_DEBUG = "DEBUG";
    private static final String LEVEL_WARN = "WARN";
    
    // Constructeur privé pour empêcher l'instanciation
    private Logger() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * Log d'information
     * @param message le message à logger
     */
    public static void info(String message) {
        log(LEVEL_INFO, message);
    }
    
    /**
     * Log d'erreur
     * @param message le message à logger
     */
    public static void error(String message) {
        log(LEVEL_ERROR, message);
    }
    
    /**
     * Log d'erreur avec exception
     * @param message le message à logger
     * @param throwable l'exception associée
     */
    public static void error(String message, Throwable throwable) {
        log(LEVEL_ERROR, message + " - " + throwable.getMessage());
    }
    
    /**
     * Log de debug
     * @param message le message à logger
     */
    public static void debug(String message) {
        log(LEVEL_DEBUG, message);
    }
    
    /**
     * Log d'avertissement
     * @param message le message à logger
     */
    public static void warn(String message) {
        log(LEVEL_WARN, message);
    }
    
    /**
     * Méthode privée de logging
     * @param level le niveau de log
     * @param message le message
     */
    private static void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logMessage = String.format("[%s] %s - %s", timestamp, level, message);
        
        // Logger personnalisé utilisant les flux standard
        // SonarQube: Acceptable pour un logger custom simple
        if (LEVEL_ERROR.equals(level)) {
            System.err.println(logMessage); // NOSONAR
        } else {
            System.out.println(logMessage); // NOSONAR
        }
    }
}