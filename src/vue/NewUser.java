package vue;

import javax.swing.*;

import metier.Utilisateur;
import persistance.AccesData;

import java.awt.*;

public class NewUser extends JDialog {

    // Variable statique pour suivre si la fenêtre est déjà ouverte
    private static NewUser instance = null;

    public static void ouvrirFenetre(JFrame parent) {
        if (instance == null || !instance.isVisible()) {
            instance = new NewUser(parent);
        } else {
            // Optionnel : Ramener la fenêtre existante au premier plan
            instance.toFront();
        }
    }


    private NewUser(JFrame parent) {
    	
    	
        super(parent, "Nouvelle Fenêtre", true); // Le troisième paramètre 'true' active la modalité
        setSize(450, 300);
        setLocationRelativeTo(parent); // Centrer par rapport au parent
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Contenu de la fenêtre (inchangé)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 300);
        add(panel);

        
        
        setVisible(true);
    }


    @Override
    public void dispose() {
        super.dispose();
        instance = null;  // Réinitialiser l'instance lorsque la fenêtre est fermée
    }
}