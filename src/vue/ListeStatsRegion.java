package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import metier.Region;
import persistance.AccesData;
import utils.Logger;

public class ListeStatsRegion {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel;
    private JButton btnNewButton;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;

    public ListeStatsRegion() {
        frame = new JFrame("Statistiques Region");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout());
        
        ImageIcon originalIcon = new ImageIcon(Menu.class.getResource("/resources/GSB.png"));  
        frame.setIconImage(originalIcon.getImage());

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        List<Region> regions = AccesData.getLesRegions();
        Logger.info("Regions récupérées : " + regions);  // Log pour vérifier les regions
        String[] regionNames = regions.stream().map(Region::getLibelleRegion).toArray(String[]::new);

        List<String> moisList = AccesData.getLesMois();
        Logger.info("Mois récupérés : " + moisList);  // Log pour vérifier les mois
        String[] moisArray = moisList.toArray(new String[0]);

        comboBox1 = new JComboBox<>(regionNames);
        comboBox2 = new JComboBox<>(moisArray);

        comboBoxPanel.add(comboBox1);
        comboBoxPanel.add(comboBox2);

        frame.getContentPane().add(comboBoxPanel, BorderLayout.NORTH);

        String[] columnNames = {"Moyenne Frais Forfait", "Moyenne Frais Hors Forfait"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        btnNewButton = new JButton("Retour");
        buttonPanel.add(btnNewButton);

        btnNewButton.addActionListener(e -> frame.setVisible(false));

        // Add ItemListeners to the JComboBox
        ItemListener itemListener = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateStats(tableModel);
            }
        };

        comboBox1.addItemListener(itemListener);
        comboBox2.addItemListener(itemListener);

        // Set default selection
        if (comboBox1.getItemCount() > 0) {
            comboBox1.setSelectedIndex(0);
        }
        if (comboBox2.getItemCount() > 0) {
            comboBox2.setSelectedIndex(0);
        }

        frame.setVisible(true);

        // Initial update with default selection
        updateStats(tableModel);
    }
    

    private void updateStats(DefaultTableModel tableModel) {
        String selectedMonth = (String) comboBox2.getSelectedItem();
        int selectedRegion = comboBox1.getSelectedIndex() + 1; // Assuming region IDs are 1-based

        Logger.info("Mois sélectionné : " + selectedMonth);
        Logger.info("ID de la région sélectionnée : " + selectedRegion);

        // Appel de la méthode combinée pour obtenir les résultats
        List<Object[]> fraisForfaitStats = AccesData.getCombinedMoyenneMontantFrais(selectedMonth, selectedRegion);
        
        // Si fraisForfaitStats est une seule ligne avec une seule valeur
        if (fraisForfaitStats != null && !fraisForfaitStats.isEmpty()) {
            // Efface les lignes précédentes
            tableModel.setRowCount(0); 

            // Ajoute les nouvelles données dans la table
            Object[] rowData = {
                fraisForfaitStats.get(0)[0], // Moyenne Frais Forfait
                fraisForfaitStats.get(0)[1]  // Moyenne Frais Hors Forfait
            };
            tableModel.addRow(rowData);

            // Log stats for debugging
            Logger.info(String.format("Moyenne Frais Forfait: %s, Moyenne Frais Hors Forfait: %s", fraisForfaitStats.get(0)[0], fraisForfaitStats.get(0)[1]));
        } else {
            Logger.warn("Aucune donnée retournée pour les paramètres sélectionnés.");
        }
    }

    
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new ListeStatsRegion();
            } catch (Exception e) {
                Logger.error("Erreur lors de l'initialisation de ListeStatsRegion", e);
                System.exit(1);
            }
        });
    }
}
