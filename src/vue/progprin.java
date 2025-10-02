package vue;
import utils.Logger;

public class ProgPrin {
    public static void main(String[] args) {
        Login loginScreen = new Login();
        loginScreen.addLoginListener((util, loginFrame) -> {
            Logger.info("Utilisateur connecté : " + util.getPrenom() + " " + util.getNom());
            loginFrame.dispose(); // Fermer la fenêtre de login
            new Menu(util);
        });

        loginScreen.setVisible(true);
    }
}