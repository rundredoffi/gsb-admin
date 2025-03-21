package listeners;

import metier.Utilisateur;

public interface LoginListener {
    void onLoginSuccess(Utilisateur util);
}