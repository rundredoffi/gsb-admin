package vue;

import javax.swing.*;

import metier.Utilisateur;
import persistance.AccesData;

import java.awt.*;

public class NouvelleFenetre extends JDialog {

    // Variable statique pour suivre si la fenêtre est déjà ouverte
    private static NouvelleFenetre instance = null;

    public static void ouvrirFenetre(JFrame parent, String firstCellValue) {
        if (instance == null || !instance.isVisible()) {
            instance = new NouvelleFenetre(parent);
        } else {
            // Optionnel : Ramener la fenêtre existante au premier plan
            instance.toFront();
        }
    }


    private NouvelleFenetre(JFrame parent) {
    	
    	
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

        
        Utilisateur util = AccesData.getUtilisateurByID(firstCellValue);

        
        JLabel label = new JLabel("Information de l'utilisateur : " + util.getNom() +" " + util.getPrenom());
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBounds(20, 10, 250, 25);
        panel.add(label);


        if (util != null) {
            JLabel labelPoints = new JLabel("Adresse : " + util.getAdresse());
            JLabel labelRebonds = new JLabel("CP : " + util.getCp());
            JLabel labelPasses = new JLabel("Ville : " + util.getVille());

            labelPoints.setBounds(50, 50, 200, 25);
            labelRebonds.setBounds(50, 90, 200, 25);
            labelPasses.setBounds(50, 130, 200, 25);

            panel.add(labelPoints);
            panel.add(labelRebonds);
            panel.add(labelPasses);

            
            
        } else {
            JLabel labelError = new JLabel("Aucune Information trouvée pour le visiteur.");
            labelError.setFont(new Font("Arial", Font.PLAIN, 14));
            labelError.setForeground(Color.RED);
            labelError.setBounds(150, 180, 200, 30);
            panel.add(labelError);
        }

        setVisible(true);
    }


    @Override
    public void dispose() {
        super.dispose();
        instance = null;  // Réinitialiser l'instance lorsque la fenêtre est fermée
    }
}