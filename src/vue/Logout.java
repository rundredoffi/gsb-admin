package vue;

import javax.swing.*;

public class Logout {
    public static void logout(JFrame currentFrame) {
        // Perform logout operations here, e.g., clearing session, etc.
        System.out.println("User logged out");

        // Close the current frame
        currentFrame.dispose();

        // Redirect to login view
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}