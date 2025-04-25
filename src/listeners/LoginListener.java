package listeners;

import metier.Utilisateur;
import javax.swing.JFrame;

public interface LoginListener {
    void onLoginSuccess(Utilisateur util, JFrame loginFrame);
}