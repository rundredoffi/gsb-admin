package vue;

import java.awt.Font;
import javax.swing.*;

import metier.Utilisateur;

public class menu {
    private JFrame frame;
    private JButton btnMatchs;
    private JButton btnStats;
    private JButton btnJoueurs;
    private JButton btnDeconnexion;

    public menu(Utilisateur util) {
        initialize(util);
        frame.setVisible(true);
    }

    private void initialize(Utilisateur util) {
        frame = new JFrame("Menu Principal");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);

        JPanel panelTitre = new JPanel();
        JLabel lblTitre = new JLabel("GSB - Bienvenue, " + util.getNom());
        lblTitre.setFont(new Font("Serif", Font.BOLD, 18));
        panelTitre.add(lblTitre);
        frame.getContentPane().add(panelTitre);

        JPanel panelBoutons = new JPanel();
        btnMatchs = new JButton("Fiches visiteurs");
        btnStats = new JButton("Statistiques");
        btnJoueurs = new JButton("Liste des visiteurs");
        btnDeconnexion = new JButton("Déconnexion");

        if (util.getRole().equals("s")) { 
            btnStats.setVisible(false);
            btnJoueurs.setVisible(false);
            panelBoutons.add(btnMatchs);
        }

        btnMatchs.addActionListener(e -> {
            frame.setVisible(false);
        });

        btnStats.addActionListener(e -> {
            frame.setVisible(false);
        });

        btnJoueurs.addActionListener(e -> {
            frame.setVisible(false);
        });

        btnDeconnexion.addActionListener(e -> {
            frame.setVisible(false);
    		new Login();
        });

        if (util.getRole().equals("r")) {
            panelBoutons.add(btnStats);
            panelBoutons.add(btnJoueurs);
        }
        
        if (util.getRole().equals("d")) {
            panelBoutons.add(btnStats);
            panelBoutons.add(btnJoueurs);
        }       
        
        panelBoutons.add(btnDeconnexion);

        frame.getContentPane().add(panelBoutons);
    }
}
