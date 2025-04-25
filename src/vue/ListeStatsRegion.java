package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import metier.Region;
import persistance.AccesData;

public class ListeStatsRegion {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel;
    private JButton btnNewButton;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private SimpleDateFormat dateFormatter;

    public ListeStatsRegion() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        frame = new JFrame("Statistiques Region");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        List<Region> regions = AccesData.getLesRegions();
        System.out.println("Regions récupérées : " + regions);  // Print pour vérifier les regions
        String[] regionNames = regions.stream().map(Region::getLibelleRegion).toArray(String[]::new);

        List<String> moisList = AccesData.getLesMois();
        System.out.println("Mois récupérés : " + moisList);  // Print pour vérifier les mois
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

        System.out.println("Mois sélectionné : " + selectedMonth);
        System.out.println("ID de la région sélectionnée : " + selectedRegion);

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

            // Print stats to console for debugging
            System.out.printf("Moyenne Frais Forfait: %s, Moyenne Frais Hors Forfait: %s%n", fraisForfaitStats.get(0)[0], fraisForfaitStats.get(0)[1]);
        } else {
            System.out.println("Aucune donnée retournée pour les paramètres sélectionnés.");
        }
    }

    
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ListeStatsRegion window = new ListeStatsRegion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
