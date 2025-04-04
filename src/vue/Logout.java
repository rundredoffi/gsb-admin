package vue;

import org.hibernate.Session;
import persistance.HibernateSession;

import javax.swing.*;

public class Logout {
    public static void logout(JFrame currentFrame) {
        // Perform logout operations here, e.g., clearing session, etc.
        System.out.println("User logged out");

        // Close the Hibernate session
        Session session = HibernateSession.getSession();
        HibernateSession.closeSession(session);

        // Close the current frame
        currentFrame.dispose();

        // Redirect to login view
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login loginScreen = new Login();
                loginScreen.addLoginListener(new Menu());
                loginScreen.setVisible(true);
            }
        });
    }
}