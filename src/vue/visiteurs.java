package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import metier.Utilisateur;
import persistance.AccesData;



public class visiteurs {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel; 
    private JButton InsererBDD;
    private JButton btnNewVisiteur;
    private JButton btnRetour;
    private JButton btnSave;
    private JLabel TextError;
    private JButton SelectXMLButton;
    private JMenuBar menuBar;
    private List<Utilisateur> utilisateursModifies; 
    private static final int[] MODIFIABLE_COLUMNS = {3, 4, 5, 6, 7, 8, 10};  // Indices des colonnes modifiables



    public visiteurs() {
    	utilisateursModifies = new ArrayList<>();
        frame = new JFrame("Fenêtre Visiteurs");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout()); // Utilisation de BorderLayout pour la disposition

        // Création du modèle de table avec des colonnes
        String[] columnNames = {"id", "Nom", "Prénom", "Adresse", "CP", "Ville", "Email", "TelFixe", "TelPortable", "DateEmbauche", "Region"};
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
                u.getRegion().getLibelleRegion(),

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
        btnNewVisiteur = new JButton("Nouveau Visiteur");
        buttonPanel.add(btnNewVisiteur);
        
        btnRetour = new JButton("Retour");
        buttonPanel.add(btnRetour);
        
        btnSave = new JButton("Enregistrer");
        buttonPanel.add(btnSave);

       
                      
       
     
        
        
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");

        

        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);


        // Liste des régions pour le JComboBox
        String[] regions = {"Région 1", "Région 2", "Région 3", "Région 4"};  // Remplacez par les vraies régions
        JComboBox<String> regionComboBox = new JComboBox<>(regions);

        // Appliquer le JComboBox comme éditeur de cellules pour la colonne "Region" (index 10)
        table.getColumnModel().getColumn(10).setCellEditor(new DefaultCellEditor(regionComboBox));
        
        // Action pour le bouton Retour
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); 
            }
        });
        
        btnNewVisiteur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewUser.ouvrirFenetre(frame);
            }
        });
        
      
        

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                DefaultTableModel model = (DefaultTableModel) e.getSource();
                Object newValue = model.getValueAt(row, column);

                if (isModifiableColumn(column)) {
                    String userId = (String) model.getValueAt(row, 0); 
                    Utilisateur utilisateur = AccesData.getUtilisateurByID(userId);

                    switch (column) {
                        case 3: // Adresse
                            utilisateur.setAdresse(newValue.toString());
                            break;
                        case 4: // CP
                            utilisateur.setCp(newValue.toString());
                            break;
                        case 5: // Ville
                            utilisateur.setVille(newValue.toString());
                            break;
                        case 6: // Email
                            utilisateur.setEmail(newValue.toString());
                            break;
                        case 7: // TelFixe
                            utilisateur.setTelfixe(newValue.toString());
                            break;
                        case 8: // TelPortable
                            utilisateur.setTelPortable(newValue.toString());
                            break;
                        case 10: 
//                        	Region reg = new Region(newValue.toString())
//                            utilisateur.setRegion());
                        	
                            break;
                        default:
                            break;
                    }

                    // Ajouter l'utilisateur modifié à la liste
                    if (!utilisateursModifies.contains(utilisateur)) {
                        utilisateursModifies.add(utilisateur);
                    }
                }
            }
        });
        
        
       
      

        // Affichage de la fenêtre
        frame.setVisible(true);
    }
    
    private boolean isModifiableColumn(int column) {
        for (int modifiableColumn : MODIFIABLE_COLUMNS) {
            if (column == modifiableColumn) {
                return true;
            }
        }
        return false;
    }
    
}