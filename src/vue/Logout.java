package vue;

import org.hibernate.Session;
import persistance.HibernateSession;
import utils.Logger;

import javax.swing.*;

public class Logout {
    
    private Logout() {
        // Constructeur privé pour empêcher l'instanciation
    }
    
    public static void logout(JFrame currentFrame) {
        // Perform logout operations here, e.g., clearing session, etc.
        Logger.info("User logged out");

        // Close the Hibernate session
        Session session = HibernateSession.getSession();
        HibernateSession.closeSession(session);

        // Close the current frame
        currentFrame.dispose();

        // Redirect to login view
        SwingUtilities.invokeLater(() -> {
            Login loginScreen = new Login();
            loginScreen.addLoginListener(new Menu());
            loginScreen.setVisible(true);
        });
    }
}