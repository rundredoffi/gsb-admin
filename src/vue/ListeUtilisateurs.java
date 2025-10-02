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
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

import metier.Region;
import metier.Utilisateur;
import persistance.AccesData;
import utils.Logger;

public class ListeUtilisateurs {
    private JFrame frame;
    private JTable table;
    private JPanel buttonPanel;
    private List<Utilisateur> utilisateursModifies;
    private List<Region> regions;
    private static final int[] MODIFIABLE_COLUMNS = {3, 4, 5, 6, 7, 8, 10}; // Indices des colonnes modifiables

    public ListeUtilisateurs(Utilisateur utilConnecte) {
        utilisateursModifies = new ArrayList<>();
        initializeFrame();
        
        DefaultTableModel tableModel = createTableModel(utilConnecte);
        setupTable(tableModel, utilConnecte);
        setupButtons(utilConnecte);
        setupMenuBar();
        setupTableListeners(utilConnecte);
        
        frame.setVisible(true);
    }
    
    private void initializeFrame() {
        frame = new JFrame("Fenêtre Visiteurs");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        ImageIcon originalIcon = new ImageIcon(Menu.class.getResource("/resources/GSB.png"));  
        frame.setIconImage(originalIcon.getImage());
    }
    
    private DefaultTableModel createTableModel(Utilisateur utilConnecte) {
        String[] columnNames = {"id", "Nom", "Prénom", "Adresse", "CP", "Ville", "Email", "TelFixe", "TelPortable", "DateEmbauche", "Region"};
        if (isAdmin(utilConnecte)) {
            columnNames = new String[]{"id", "Nom", "Prénom", "Adresse", "CP", "Ville", "Email", "TelFixe", "TelPortable", "DateEmbauche", "Region", "Supprimer"};
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        populateTableModel(tableModel, utilConnecte);
        return tableModel;
    }
    
    private void populateTableModel(DefaultTableModel tableModel, Utilisateur utilConnecte) {
        List<Utilisateur> util = AccesData.getLesUtilisateur();
        for (Utilisateur u : util) {
            Object[] rowData = createRowData(u, utilConnecte);
            tableModel.addRow(rowData);
        }
    }
    
    private Object[] createRowData(Utilisateur u, Utilisateur utilConnecte) {
        Object[] baseData = {
            u.getIdUtilisateur(), u.getNom(), u.getPrenom(), u.getAdresse(),
            u.getCp(), u.getVille(), u.getEmail(), u.getTelfixe(),
            u.getTelPortable(), u.getDateEmbauche(), u.getRegion().getLibelleRegion()
        };
        
        if (isAdmin(utilConnecte)) {
            Object[] extendedData = new Object[baseData.length + 1];
            System.arraycopy(baseData, 0, extendedData, 0, baseData.length);
            extendedData[baseData.length] = "❌";
            return extendedData;
        }
        return baseData;
    }
    
    private void setupTable(DefaultTableModel tableModel, Utilisateur utilConnecte) {
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isAdmin(utilConnecte) && isModifiableColumn(column);
            }
        };
        
        configureTableAppearance();
        setupRegionComboBox(utilConnecte);
        
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void configureTableAppearance() {
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void setupRegionComboBox(Utilisateur utilConnecte) {
        regions = AccesData.getLesRegions();
        String[] regionNames = regions.stream().map(Region::getLibelleRegion).toArray(String[]::new);
        JComboBox<String> regionComboBox = new JComboBox<>(regionNames);
        
        if (!isAdmin(utilConnecte)) {
            regionComboBox.setEditable(false);
        }
        
        TableColumn regionColumn = table.getColumnModel().getColumn(10);
        regionColumn.setCellEditor(new DefaultCellEditor(regionComboBox));
    }
    
    private void setupButtons(Utilisateur utilConnecte) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        if (isAdmin(utilConnecte)) {
            setupAdminButtons();
            setupDeleteColumn();
        }
        
        setupReturnButton();
    }
    
    private void setupAdminButtons() {
        JButton btnNewVisiteur = new JButton("Nouveau Visiteur");
        buttonPanel.add(btnNewVisiteur);
        btnNewVisiteur.addActionListener(e -> CreateUtilisateur.ouvrirFenetre(frame));
        
        JButton btnSave = new JButton("Enregistrer");
        buttonPanel.add(btnSave);
        btnSave.addActionListener(e -> {
            for (Utilisateur utilisateur : utilisateursModifies) {
                AccesData.updateVisiteur(utilisateur);
            }
            utilisateursModifies.clear();
            JOptionPane.showMessageDialog(frame, "Modifications enregistrées avec succès !");
        });
    }
    
    private void setupDeleteColumn() {
        TableColumn deleteColumn = table.getColumnModel().getColumn(11);
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ButtonEditor());
    }
    
    private void setupReturnButton() {
        JButton btnRetour = new JButton("Retour");
        buttonPanel.add(btnRetour);
        btnRetour.addActionListener(e -> frame.setVisible(false));
    }
    
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }
    
    private void setupTableListeners(Utilisateur utilConnecte) {
        setupSelectionListener();
        setupTableModelListener();
    }
    
    private void setupSelectionListener() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Object firstCellValue = table.getValueAt(selectedRow, 0);
                    if (firstCellValue != null) {
                        FicheUtilisateur.ouvrirFenetre(frame, firstCellValue.toString());
                    }
                }
            }
        });
    }
    
    private void setupTableModelListener() {
        table.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column != TableModelEvent.ALL_COLUMNS && isModifiableColumn(column)) {
                handleCellModification(e, row, column);
            }
        });
    }
    
    private void handleCellModification(TableModelEvent e, int row, int column) {
        DefaultTableModel model = (DefaultTableModel) e.getSource();
        Object newValue = model.getValueAt(row, column);
        String userId = (String) model.getValueAt(row, 0);
        Utilisateur utilisateur = AccesData.getUtilisateurByID(userId);
        
        updateUserField(utilisateur, column, newValue);
        
        if (!utilisateursModifies.contains(utilisateur)) {
            utilisateursModifies.add(utilisateur);
        }
    }
    
    private void updateUserField(Utilisateur utilisateur, int column, Object newValue) {
        switch (column) {
            case 3: utilisateur.setAdresse(newValue.toString()); break;
            case 4: utilisateur.setCp(newValue.toString()); break;
            case 5: utilisateur.setVille(newValue.toString()); break;
            case 6: utilisateur.setEmail(newValue.toString()); break;
            case 7: utilisateur.setTelfixe(newValue.toString()); break;
            case 8: utilisateur.setTelPortable(newValue.toString()); break;
            case 10: updateUserRegion(utilisateur, newValue.toString()); break;
            default: break;
        }
    }
    
    private void updateUserRegion(Utilisateur utilisateur, String selectedRegion) {
        for (Region region : regions) {
            if (region.getLibelleRegion().equals(selectedRegion)) {
                utilisateur.setRegion(region);
                break;
            }
        }
    }
    
    private boolean isAdmin(Utilisateur utilisateur) {
        return utilisateur.getRole().getIdRole().equals("s");
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
                        Logger.error("Invalid row index: " + row);
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
    }
}