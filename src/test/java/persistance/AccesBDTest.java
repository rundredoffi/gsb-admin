package persistance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe AccesBD
 */
class AccesBDTest {
    
    @BeforeEach
    void setUp() {
        // Reset de la connexion statique avant chaque test
        AccesBD.close();
        // Force reset de la variable statique connection
        try {
            var field = AccesBD.class.getDeclaredField("con");
            field.setAccessible(true);
            field.set(null, null);
        } catch (Exception e) {
            // Ignore si impossible d'accéder au champ
        }
    }
    
    @AfterEach
    void tearDown() {
        AccesBD.close();
    }
    
    @Test
    @DisplayName("Le constructeur doit lever une exception IllegalStateException")
    void constructor_ShouldThrowIllegalStateException() {
        assertThatThrownBy(() -> {
            // Utilisation de la réflexion pour tester le constructeur privé
            java.lang.reflect.Constructor<AccesBD> constructor = AccesBD.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        })
        .hasCauseInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Utility class");
    }
    
    @Test
    @DisplayName("getInstance doit retourner une connexion valide")
    void getInstance_ShouldReturnValidConnection() throws SQLException {
        // When
        Connection connection = AccesBD.getInstance();
        
        // Then
        assertThat(connection).isNotNull();
        assertThat(connection.isClosed()).isFalse();
        assertThat(connection.isValid(5)).isTrue();
    }
    
    @Test
    @DisplayName("getInstance doit retourner la même instance (singleton)")
    void getInstance_ShouldReturnSameInstance() {
        // When
        Connection connection1 = AccesBD.getInstance();
        Connection connection2 = AccesBD.getInstance();
        
        // Then
        assertThat(connection1).isSameAs(connection2);
    }
    
    @Test
    @DisplayName("close doit fermer la connexion correctement")
    void close_ShouldCloseConnectionCorrectly() throws SQLException {
        // Given
        Connection connection = AccesBD.getInstance();
        assertThat(connection.isClosed()).isFalse();
        
        // When
        AccesBD.close();
        
        // Then
        assertThat(connection.isClosed()).isTrue();
    }
    
    @Test
    @DisplayName("close sur une connexion nulle ne doit pas lever d'exception")
    void close_WithNullConnection_ShouldNotThrowException() {
        // Given - pas de connexion créée
        
        // When/Then
        assertThatCode(() -> AccesBD.close()).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("close sur une connexion déjà fermée ne doit pas lever d'exception")
    void close_WithAlreadyClosedConnection_ShouldNotThrowException() throws SQLException {
        // Given
        Connection connection = AccesBD.getInstance();
        connection.close(); // Fermeture manuelle
        
        // When/Then
        assertThatCode(() -> AccesBD.close()).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("getInstance après close doit créer une nouvelle connexion")
    void getInstance_AfterClose_ShouldCreateNewConnection() throws SQLException {
        // Given
        Connection firstConnection = AccesBD.getInstance();
        AccesBD.close();
        
        // When
        Connection secondConnection = AccesBD.getInstance();
        
        // Then
        assertThat(secondConnection).isNotNull();
        assertThat(secondConnection.isClosed()).isFalse();
        assertThat(firstConnection.isClosed()).isTrue();
    }
    
    @Test
    @DisplayName("La connexion doit être une base H2 en mémoire pour les tests")
    void connection_ShouldBeH2InMemoryForTests() throws SQLException {
        // When
        Connection connection = AccesBD.getInstance();
        
        // Then
        String url = connection.getMetaData().getURL();
        assertThat(url).contains("h2:mem:");
        assertThat(connection.getMetaData().getDatabaseProductName()).isEqualTo("H2");
    }
    
    @Test
    @DisplayName("La connexion doit supporter les transactions")
    void connection_ShouldSupportTransactions() throws SQLException {
        // When
        Connection connection = AccesBD.getInstance();
        
        // Then
        assertThat(connection.getMetaData().supportsTransactions()).isTrue();
        assertThat(connection.getAutoCommit()).isTrue(); // Par défaut
    }
    
    @Test
    @DisplayName("La connexion doit permettre d'exécuter des requêtes SQL basiques")
    void connection_ShouldAllowBasicSQLQueries() throws SQLException {
        // Given
        Connection connection = AccesBD.getInstance();
        
        // When/Then
        assertThatCode(() -> {
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("SELECT 1 as test");
            assertThat(resultSet.next()).isTrue();
            assertThat(resultSet.getInt("test")).isEqualTo(1);
            resultSet.close();
            statement.close();
        }).doesNotThrowAnyException();
    }
}