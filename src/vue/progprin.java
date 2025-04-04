package vue;
import persistance.HibernateSession;

import javax.swing.JFrame;

import listeners.LoginListener;
import metier.Utilisateur;

public class progprin {
    public static void main(String[] args) {
        Login loginScreen = new Login();
        loginScreen.addLoginListener(new LoginListener() {
            @Override
            public void onLoginSuccess(Utilisateur util, JFrame loginFrame) {
                System.out.println("Utilisateur connecté : " + util.getPrenom() + " " + util.getNom());
                loginFrame.dispose(); // Fermer la fenêtre de login
                new Menu(util);
            }
        });

        loginScreen.setVisible(true);
    }
}