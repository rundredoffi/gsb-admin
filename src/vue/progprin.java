package vue;
import persistance.HibernateSession;
import listeners.LoginListener;
import metier.Utilisateur;

public class progprin {
    public static void main(String[] args) {
        Login loginScreen = new Login();
        loginScreen.setVisible(true);
        loginScreen.addLoginListener((LoginListener) new LoginListener() {
            public void onLoginSuccess(Utilisateur utilConnecte) {
                System.out.println("Utilisateur connecté : " + utilConnecte.getPrenom() + " " + utilConnecte.getNom());
                loginScreen.dispose(); // Fermer la fenêtre de login
                new Menu(utilConnecte);
            }
        });
    }
}