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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import metier.FicheFrais;
import metier.Region;
import persistance.AccesData;

public class fichefrais {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel; // Panneau pour les boutons
    private JButton InsererBDD;
    private JButton btnNewButton;
    private JLabel TextError;
    private JButton SelectXMLButton;
    private JMenuBar menuBar;
    private List<Region> regions;
    private List<String> mois;
    private SimpleDateFormat dateFormatter;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private DefaultTableModel tableModel;

    public fichefrais() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // Création de la fenêtre
        frame = new JFrame("Fenêtre Visiteurs");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout()); 

        // Création du panneau pour les JComboBox
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        regions = AccesData.getLesRegions();
        String[] regionNames = regions.stream().map(Region::getLibelleRegion).toArray(String[]::new);

        List<String> moisList = AccesData.getLesMois();
        String[] moisArray = moisList.toArray(new String[0]);

        
        // Création des JComboBox
        comboBox1 = new JComboBox<>(regionNames);
        comboBox2 = new JComboBox<>(moisArray);
        JComboBox<String> comboBox2 = new JComboBox<>(moisArray);

      

        // Ajout des JComboBox au panneau
        comboBoxPanel.add(comboBox1);
        comboBoxPanel.add(comboBox2);

        // Ajout du panneau des JComboBox à la partie nord de la fenêtre
        frame.getContentPane().add(comboBoxPanel, BorderLayout.NORTH);

        // Création du modèle de table avec des colonnes
        String[] columnNames = {"idVisiteur ", "mois ", "nbJustificatif", "montantValide", "dateModif", "idEtat "};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<FicheFrais> ff = AccesData.getLesFicheFrais();
        for (FicheFrais f : ff) {
            Object[] rowData = {
                f.getId(),
                f.getDateModif(),
                f.getNbJustificatif(),
                f.getMontantValide(),
                f.getDateModif(),
                f.getEtat().getLibelleEtat()
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

    private void updateTableModel() {
        tableModel.setRowCount(0);
        String selectedRegion = (String) comboBox1.getSelectedItem();
        String selectedMois = (String) comboBox2.getSelectedItem();
        Region region = regions.stream().filter(r -> r.getLibelleRegion().equals(selectedRegion)).findFirst().orElse(null);

        if (region != null) {
            List<FicheFrais> ff = AccesData.retrieveFicheFraisByRegion(region.getIdRegion());
            for (FicheFrais f : ff) {
                if (selectedMois == null || selectedMois.equals(f.getId().getMois())) {
                    Object[] rowData = {
                        f.getId().getIdVisiteur(),
                        f.getId().getMois(),
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
                    fichefrais window = new fichefrais();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
