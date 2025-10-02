package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger centralisé pour l'application GSB-Admin
 * Fournit des méthodes de logging avec différents niveaux de gravité
 */
public class Logger {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Constructeur privé pour empêcher l'instanciation
    private Logger() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * Log d'information
     * @param message le message à logger
     */
    public static void info(String message) {
        log("INFO", message);
    }
    
    /**
     * Log d'erreur
     * @param message le message à logger
     */
    public static void error(String message) {
        log("ERROR", message);
    }
    
    /**
     * Log d'erreur avec exception
     * @param message le message à logger
     * @param throwable l'exception associée
     */
    public static void error(String message, Throwable throwable) {
        log("ERROR", message + " - " + throwable.getMessage());
    }
    
    /**
     * Log de debug
     * @param message le message à logger
     */
    public static void debug(String message) {
        log("DEBUG", message);
    }
    
    /**
     * Log d'avertissement
     * @param message le message à logger
     */
    public static void warn(String message) {
        log("WARN", message);
    }
    
    /**
     * Méthode privée de logging
     * @param level le niveau de log
     * @param message le message
     */
    private static void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logMessage = String.format("[%s] %s - %s", timestamp, level, message);
        
        // Pour les erreurs, utiliser stderr, sinon stdout
        if ("ERROR".equals(level)) {
            System.err.println(logMessage);
        } else {
            System.out.println(logMessage);
        }
    }
}