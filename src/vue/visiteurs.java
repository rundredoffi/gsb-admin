package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import metier.Utilisateur;
import persistance.AccesData;



public class visiteurs {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel; // Panneau pour les boutons
    private JButton InsererBDD;
    private JButton btnNewButton;
    private JLabel TextError;
    private JButton SelectXMLButton;
    private JMenuBar menuBar;


    public visiteurs() {
        // Création de la fenêtre
        frame = new JFrame("Fenêtre Visiteurs");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout()); // Utilisation de BorderLayout pour la disposition

        // Création du modèle de table avec des colonnes
        String[] columnNames = {"id", "Nom", "Prénom", "Adresse", "CP", "Ville", "Email", "TelFixe", "TelPortable", "DateEmbauche"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<Utilisateur> util = AccesData.getLesUtilisateur();
        for (Utilisateur u : util) {
            Object[] rowData = {
                u.getIdUtilisateur(),
                u.getNom(),
                u.getPrenom(),
                u.getAdresse(),
                u.getCp(),
                u.getVille(),
                u.getEmail(),
                u.getTelfixe(),
                u.getTelPortable(),
                u.getDateEmbauche(),
            };
            tableModel.addRow(rowData);
        }

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Permet une seule ligne sélectionnée à la fois

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER); // Ajouter la table au centre de la fenêtre

        // Création du panneau pour les boutons (placé en bas)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout pour centrer les boutons
        frame.add(buttonPanel, BorderLayout.SOUTH); // Ajouter le panneau des boutons en bas

        // Ajout des boutons dans le panneau
        btnNewButton = new JButton("Retour");
        buttonPanel.add(btnNewButton);

       
     
        
        
        // Créer une barre de menu (JMenuBar)
        menuBar = new JMenuBar();

        // Créer un menu (JMenu)
        JMenu fileMenu = new JMenu("Menu");

        // Créer des éléments de menu (JMenuItem)
//        JMenuItem matchItem = new JMenuItem("Match");
//        JMenuItem statItem = new JMenuItem("Stats");
//        JMenuItem joueurItem = new JMenuItem("Joueurs");
//        JMenuItem exitItem = new JMenuItem("Quitter");

        // Ajouter les éléments au menu "Fichier"
//        fileMenu.add(matchItem);
//        fileMenu.add(statItem);
//        fileMenu.add(joueurItem);
//        fileMenu.addSeparator();  // Séparateur entre les éléments de menu
//        fileMenu.add(exitItem);

        // Ajouter le menu "Fichier" à la barre de menu
        menuBar.add(fileMenu);

        // Assigner la barre de menu à la fenêtre
        frame.setJMenuBar(menuBar);


        // Action pour le bouton "Matchs"
//        matchItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                frame.setVisible(false); // Masquer la fenêtre d'accueil
//            }
//        });

        // Action pour le bouton "Stats"
//        statItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                frame.setVisible(false); // Masquer la fenêtre d'accueil
//                new stats(); // Ouvrir la nouvelle fenêtre "Stats"
//            }
//        });

        // Action pour le bouton "Joueurs"
//        joueurItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                frame.setVisible(false); // Masquer la fenêtre d'accueil
//            }
//        });
        
        
//        exitItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);  // Quitter l'application
//            }
//        });
//        
//        
        
        // Action pour le bouton Retour
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre actuelle
            }
        });
        
      

        // Affichage de la fenêtre
        frame.setVisible(true);
    }
}