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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import metier.Region;
import persistance.AccesData;
import utils.Outils;

public class ListeStatsFiches {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel;
    private JButton btnNewButton;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;

    public ListeStatsFiches() {
        frame = new JFrame("Statistiques Fiches");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        ImageIcon originalIcon = new ImageIcon(Menu.class.getResource("/resources/GSB.png"));  
        frame.setIconImage(originalIcon.getImage());
        
        List<Region> regions = AccesData.getLesRegions();
        String[] regionNames = regions.stream().map(Region::getLibelleRegion).toArray(String[]::new);
        String[] moisArray = Outils.getMoisFormat();

        comboBox1 = new JComboBox<>(regionNames);
        comboBox2 = new JComboBox<>(moisArray);

        comboBoxPanel.add(comboBox1);
        comboBoxPanel.add(comboBox2);

        frame.getContentPane().add(comboBoxPanel, BorderLayout.NORTH);

        String[] columnNames = {"idUtilisateur", "Nom", "Prenom", "Montant Frais Forfait", "Montant Frais Hors Forfait", "nbFraisHorsForfait"};
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
        updateStats(tableModel);
    }

    private void updateStats(DefaultTableModel tableModel) {
        String selectedMois = (String) comboBox2.getSelectedItem();
        int selectedRegion = comboBox1.getSelectedIndex() + 1;
        String selectedMoisFormat = Outils.formatageMoisSQL(selectedMois);
        List<Object[]> stats = AccesData.getCombinedStats(selectedMoisFormat, selectedRegion);
        tableModel.setRowCount(0); // Clear existing data

        for (Object[] stat : stats) {
            if (stat.length >= 6) {
                Object[] rowData = {
                    stat[0], // idUtilisateur
                    stat[1], // Nom
                    stat[2], // Prenom
                    stat[3], // Montant Frais Forfait
                    stat[4], // Montant Frais Hors Forfait
                    stat[5]  // nbFraisHorsForfait
                };
                tableModel.addRow(rowData);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ListeStatsFiches window = new ListeStatsFiches();
            } catch (Exception e) {
                System.err.println("Erreur lors de l'initialisation de ListeStatsFiches : " + e.getMessage());
                System.exit(1);
            }
        });
    }
}
