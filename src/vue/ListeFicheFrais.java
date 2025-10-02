package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import metier.FicheFrais;
import metier.Region;
import persistance.AccesData;
import utils.outils;

public class ListeFicheFrais {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel; // Panneau pour les boutons
    private JButton btnNewButton;
    private JMenuBar menuBar;
    private List<Region> regions;
    private SimpleDateFormat dateFormatter;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private DefaultTableModel tableModel;

    public ListeFicheFrais() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // Création de la fenêtre
        frame = new JFrame("Fenêtre ListeFicheFrais");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout()); 

        ImageIcon originalIcon = new ImageIcon(Menu.class.getResource("/resources/GSB.png"));  
        frame.setIconImage(originalIcon.getImage());
        
        // Création du panneau pour les JComboBox
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        regions = AccesData.getLesRegions();
        String[] regionNames = regions.stream().map(Region::getLibelleRegion).toArray(String[]::new);
        String[] moisArray = outils.getMoisFormat();

        // Création des JComboBox
        comboBox1 = new JComboBox<>(regionNames);
        comboBox2 = new JComboBox<>(moisArray);
        

        // Ajout des JComboBox au panneau
        comboBoxPanel.add(comboBox1);
        comboBoxPanel.add(comboBox2);

        // Ajout du panneau des JComboBox à la partie nord de la fenêtre
        frame.getContentPane().add(comboBoxPanel, BorderLayout.NORTH);

        // Création du modèle de table avec des colonnes
        String[] columnNames = {"idVisiteur", "mois", "nbJustificatifs", "montantValide", "dateModif", "idEtat"};
        tableModel = new DefaultTableModel(columnNames, 0);

        updateTableModel();

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Permet une seule ligne sélectionnée à la fois

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Création du panneau pour les boutons (placé en bas)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout pour centrer les boutons
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH); // Ajouter le panneau des boutons en bas

        // Ajout des boutons dans le panneau
        btnNewButton = new JButton("Retour");
        buttonPanel.add(btnNewButton);

        // Créer une barre de menu (JMenuBar)
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Action pour le bouton Retour
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Masquer la fenêtre actuelle
            }
        });

        // Ajouter des écouteurs d'événements aux JComboBox
        comboBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateTableModel();
                }
            }
        });

        comboBox2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateTableModel();
                }
            }
        });

        // Affichage de la fenêtre
        frame.setVisible(true);
    }
    
    private String reformatMois(String mois) {
        // Convertir le mois au format yyyyMM en LocalDate
        LocalDate date = LocalDate.parse(mois + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        // Reformater la date au format MM/yyyy
        return date.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    private void updateTableModel() {
        tableModel.setRowCount(0);
        String selectedRegion = (String) comboBox1.getSelectedItem();
        String selectedMois = (String) comboBox2.getSelectedItem();
        Region region = regions.stream().filter(r -> r.getLibelleRegion().equals(selectedRegion)).findFirst().orElse(null);
        String MoisSelectedFormated = outils.formatageMoisSQL(selectedMois);
        
        
        if (region != null) {
            List<FicheFrais> ff = AccesData.retrieveFicheFraisByRegion(region.getIdRegion());
            for (FicheFrais f : ff) {
                if (selectedMois == null || MoisSelectedFormated.equals(f.getId().getMois())) {
                    Object[] rowData = {
                        f.getId().getIdVisiteur(),
                        reformatMois(f.getId().getMois()), 
                        f.getNbJustificatif(),
                        f.getMontantValide(),
                        dateFormatter.format(f.getDateModif()),
                        f.getEtat().getLibelleEtat(),
                    };
                    tableModel.addRow(rowData);
                }
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ListeFicheFrais();
                } catch (Exception e) {
                    System.err.println("Erreur lors de l'initialisation de ListeFicheFrais : " + e.getMessage());
                    System.exit(1); // Quitter l'application en cas d'erreur critique
                }
            }
        });
    }
}