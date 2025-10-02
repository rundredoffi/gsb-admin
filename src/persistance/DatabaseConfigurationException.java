package persistance;

/**
 * Exception dédiée pour les erreurs de configuration de base de données
 */
public class DatabaseConfigurationException extends RuntimeException {
    
    /**
     * Constructeur avec message d'erreur
     * @param message le message d'erreur
     */
    public DatabaseConfigurationException(String message) {
        super(message);
    }
    
    /**
     * Constructeur avec message d'erreur et cause
     * @param message le message d'erreur
     * @param cause la cause de l'exception
     */
    public DatabaseConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
