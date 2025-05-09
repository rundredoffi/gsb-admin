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
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

import metier.Region;
import metier.Utilisateur;
import persistance.AccesData;

public class ListeUtilisateurs {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel;
    private JButton btnNewVisiteur;
    private JButton btnRetour;
    private JButton btnSave;
    private JMenuBar menuBar;
    private List<Utilisateur> utilisateursModifies;
    private List<Region> regions;
    private static final int[] MODIFIABLE_COLUMNS = {3, 4, 5, 6, 7, 8, 10}; // Indices des colonnes modifiables

    public ListeUtilisateurs(Utilisateur utilConnecte) {
        utilisateursModifies = new ArrayList<>();
        frame = new JFrame("Fenêtre Visiteurs");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Création du modèle de table avec des colonnes
        String[] columnNames = {"id", "Nom", "Prénom", "Adresse", "CP", "Ville", "Email", "TelFixe", "TelPortable", "DateEmbauche", "Region", "Supprimer"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        regions = AccesData.getLesRegions();
        String[] regionNames = regions.stream().map(Region::getLibelleRegion).toArray(String[]::new);

        JComboBox<String> regionComboBox = new JComboBox<>(regionNames);
        if(!utilConnecte.getRole().getIdRole().equals("s")) {
        	regionComboBox.setEditable(false);
        }

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
                "❌"
            };
            tableModel.addRow(rowData);
        }

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumn regionColumn = table.getColumnModel().getColumn(10);
        regionColumn.setCellEditor(new DefaultCellEditor(regionComboBox));

        if(utilConnecte.getRole().getIdRole().equals("s")) {
        	TableColumn deleteColumn = table.getColumnModel().getColumn(11);
            deleteColumn.setCellRenderer(new ButtonRenderer());
            deleteColumn.setCellEditor(new ButtonEditor());
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(buttonPanel, BorderLayout.SOUTH);

        if(utilConnecte.getRole().getIdRole().equals("s")) {
        	btnNewVisiteur = new JButton("Nouveau Visiteur");
            buttonPanel.add(btnNewVisiteur);
            btnNewVisiteur.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CreateUtilisateur.ouvrirFenetre(frame);
                }
            });
        }

        btnRetour = new JButton("Retour");
        buttonPanel.add(btnRetour);

        if(utilConnecte.getRole().getIdRole().equals("s")) {
        	btnSave = new JButton("Enregistrer");
            buttonPanel.add(btnSave);
            btnSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (Utilisateur utilisateur : utilisateursModifies) {
                        AccesData.updateVisiteur(utilisateur);
                    }
                    utilisateursModifies.clear();
                    JOptionPane.showMessageDialog(frame, "Modifications enregistrées avec succès !");
                }
            });
        }

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });



        
        
     // Ajout d'un listener pour détecter la sélection
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object firstCellValue = table.getValueAt(selectedRow, 0);
                        if (firstCellValue != null) {
                            // Passez 'frame' comme parent ici
                        	FicheUtilisateur.ouvrirFenetre(frame, firstCellValue.toString());
                        }
                    }
                }
            }
        });

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column != TableModelEvent.ALL_COLUMNS) {
                    DefaultTableModel model = (DefaultTableModel) e.getSource();
                    Object newValue = model.getValueAt(row, column);

                    if (isModifiableColumn(column)) {
                        String userId = (String) model.getValueAt(row, 0);
                        Utilisateur utilisateur = AccesData.getUtilisateurByID(userId);

                        switch (column) {
                            case 3: utilisateur.setAdresse(newValue.toString()); break;
                            case 4: utilisateur.setCp(newValue.toString()); break;
                            case 5: utilisateur.setVille(newValue.toString()); break;
                            case 6: utilisateur.setEmail(newValue.toString()); break;
                            case 7: utilisateur.setTelfixe(newValue.toString()); break;
                            case 8: utilisateur.setTelPortable(newValue.toString()); break;
                            case 10: 
                                String selectedRegion = newValue.toString();
                                for (Region region : regions) {
                                    if (region.getLibelleRegion().equals(selectedRegion)) {
                                        utilisateur.setRegion(region);
                                        break;
                                    }
                                }
                                break;
                            default: break;
                        }

                        if (!utilisateursModifies.contains(utilisateur)) {
                            utilisateursModifies.add(utilisateur);
                        }
                    }
                }
            }
        });

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

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setForeground(Color.RED);
            setBorderPainted(false);
            setContentAreaFilled(false);
            return this;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JButton button;
        private String label;
        private boolean isPushed;
        private int row;

        public ButtonEditor() {
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(this);
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            button.setForeground(Color.RED);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            isPushed = true;
            return button;
        }
        
        

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                int confirm = JOptionPane.showConfirmDialog(button, "Êtes-vous sûr de vouloir supprimer cet utilisateur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (row >= 0 && row < table.getRowCount()) { // Ensure row index is within valid range
                        String utilisateurId = (String) table.getValueAt(row, 0);
                        if (AccesData.deleteVisiteur(utilisateurId)) {
                            ((DefaultTableModel) table.getModel()).removeRow(row);
                        } else {
                            JOptionPane.showMessageDialog(button, "Échec de la suppression");
                        }
                    } else {
                        System.err.println("Invalid row index: " + row);
                    }
                }
            }
            isPushed = false;
            return label;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
        }
    }}