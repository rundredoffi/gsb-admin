package vue;

import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

import metier.Utilisateur;

public class Menu {
    private JFrame frame;
    private JButton btnDeconnexion;

    public Menu(Utilisateur util) {
        initialize(util);
        frame.setVisible(true);
    }

    private void initialize(Utilisateur util) {
        frame = new JFrame("Menu Principal");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);

        JPanel panelTitre = new JPanel();
        
        // Load and resize the image
        ImageIcon originalIcon = new ImageIcon(Menu.class.getResource("/resources/GSB.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(50, 20, Image.SCALE_SMOOTH); // Change width and height as needed
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create a JLabel and set the resized image icon
        JLabel lblImageGSB = new JLabel("");
        lblImageGSB.setIcon(resizedIcon);
        panelTitre.add(lblImageGSB);

        JLabel lblTitre = new JLabel("GSB - Bienvenue, " + util.getNom() + ", " + util.getRole().getLibelleRole());
        lblTitre.setFont(new Font("Serif", Font.BOLD, 18));
        panelTitre.add(lblTitre);
        frame.getContentPane().add(panelTitre);

        JPanel panelBoutons = new JPanel();
        JButton btnGestionVisiteurs = new JButton("Gérer les visiteurs");
        JButton btnConsulterFicheVisiteurs = new JButton("Consulter les fiches des visiteurs");
        JButton btnConsulterFicheFrais = new JButton("Consulter les FicheFrais");
        JButton btnConsulterStats = new JButton("Consulter les statistiques");
        btnDeconnexion = new JButton("Déconnexion");
        if (util.getRole().getIdRole().equals("s")) { 
        	panelBoutons.add(btnGestionVisiteurs);
        	btnGestionVisiteurs.addActionListener(e -> new ListeUtilisateurs(util));
        }
        
        if (util.getRole().getIdRole().equals("r")) {
            panelBoutons.add(btnConsulterStats);
            panelBoutons.add(btnConsulterFicheFrais);
            btnConsulterFicheFrais.addActionListener(e -> new ListeFicheFrais());
            btnConsulterStats.addActionListener(e -> new ListeStatsFiches());
        }
        
        if (util.getRole().getIdRole().equals("d")) {
        	btnGestionVisiteurs.setText("Consulter les fiches visiteur");
            panelBoutons.add(btnGestionVisiteurs);
        	btnGestionVisiteurs.addActionListener(e -> new ListeUtilisateurs(util));
        }       
        
        btnDeconnexion.addActionListener(e -> System.exit(0));
        panelBoutons.add(btnDeconnexion); 
        
        frame.getContentPane().add(panelBoutons);      
    }
}