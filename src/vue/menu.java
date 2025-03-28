package vue;

import java.awt.Font;

import javax.swing.*;

import metier.Utilisateur;

public class menu {
    private JFrame frame;
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
        JLabel lblTitre = new JLabel("GSB - Bienvenue, " + util.getNom() + ", " + util.getRole().getLibelleRole());
        lblTitre.setFont(new Font("Serif", Font.BOLD, 18));
        panelTitre.add(lblTitre);
        frame.getContentPane().add(panelTitre);

        JPanel panelBoutons = new JPanel();
        JButton btnGestionVisiteurs = new JButton("Gérer les visiteurs");
        JButton btnConsulterFicheVisiteurs = new JButton("Consulter les fiches des visiteurs");
        JButton btnConsulterStats = new JButton("Consulter les statistiques");
        btnDeconnexion = new JButton("Déconnexion");
        if (util.getRole().getIdRole().equals("s")) { 
        	panelBoutons.add(btnGestionVisiteurs);
        	panelBoutons.add(btnConsulterFicheVisiteurs);
        	btnGestionVisiteurs.addActionListener(e -> new visiteurs());
        }
        
        if (util.getRole().getIdRole().equals("r")) {
            panelBoutons.add(btnConsulterStats);
        }
        
        if (util.getRole().getIdRole().equals("d")) {
            panelBoutons.add(btnConsulterFicheVisiteurs);
        }       
        
     
        btnDeconnexion.addActionListener(e -> System.exit(0));
        panelBoutons.add(btnDeconnexion);
        frame.getContentPane().add(panelBoutons);      
    }

	
      
}
