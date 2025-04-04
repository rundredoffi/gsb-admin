package vue;

import javax.swing.*;

import metier.Region;
import metier.Role;
import metier.Utilisateur;
import metier.FicheFrais;

import persistance.AccesData;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateUtilisateur extends JDialog {

    private static CreateUtilisateur instance = null;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField loginField;
    private JTextField passwordField;
    private JTextField adresseField;
    private JTextField codePostalField;
    private JTextField villeField;
    private JTextField idVisiteurField;
    private JTextField emailField;
    private JTextField numFixeField;
    private JTextField numPortableField;
    private JComboBox<Region> regionComboBox;

    public static void ouvrirFenetre(JFrame parent) {
        if (instance == null || !instance.isVisible()) {
            instance = new CreateUtilisateur(parent);
        } else {
            instance.toFront();
        }
    }

    private CreateUtilisateur(JFrame parent) {
        super(parent, "Nouvelle Fenêtre", true);
        setSize(450, 650);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 300);
        getContentPane().add(panel);

        nomField = new JTextField();
        nomField.setBounds(26, 59, 165, 20);
        panel.add(nomField);
        nomField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Nom : ");
        lblNewLabel.setBounds(26, 34, 165, 14);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Prénom : ");
        lblNewLabel_1.setBounds(246, 34, 165, 14);
        panel.add(lblNewLabel_1);

        prenomField = new JTextField();
        prenomField.setBounds(246, 59, 165, 20);
        panel.add(prenomField);
        prenomField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Login : ");
        lblNewLabel_2.setBounds(246, 108, 165, 14);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Mot de passe : ");
        lblNewLabel_3.setBounds(26, 177, 165, 14);
        panel.add(lblNewLabel_3);

        loginField = new JTextField();
        loginField.setBounds(246, 133, 165, 20);
        panel.add(loginField);
        loginField.setColumns(10);

        passwordField = new JTextField();
        passwordField.setBounds(26, 202, 165, 20);
        panel.add(passwordField);
        passwordField.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Adresse : ");
        lblNewLabel_4.setBounds(246, 244, 165, 14);
        panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Code Postal : ");
        lblNewLabel_5.setBounds(246, 177, 165, 14);
        panel.add(lblNewLabel_5);

        adresseField = new JTextField();
        adresseField.setBounds(246, 275, 165, 20);
        panel.add(adresseField);
        adresseField.setColumns(10);

        codePostalField = new JTextField();
        codePostalField.setBounds(246, 202, 165, 20);
        panel.add(codePostalField);
        codePostalField.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Ville : ");
        lblNewLabel_6.setBounds(26, 244, 165, 14);
        panel.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Identifiant visiteur");
        lblNewLabel_7.setBounds(26, 108, 165, 14);
        panel.add(lblNewLabel_7);

        villeField = new JTextField();
        villeField.setBounds(26, 275, 165, 20);
        panel.add(villeField);
        villeField.setColumns(10);

        idVisiteurField = new JTextField();
        idVisiteurField.setBounds(26, 133, 165, 20);
        panel.add(idVisiteurField);
        idVisiteurField.setColumns(10);

        JLabel lblNewLabel_9 = new JLabel("Adresse Email : ");
        lblNewLabel_9.setBounds(26, 325, 165, 14);
        panel.add(lblNewLabel_9);

        emailField = new JTextField();
        emailField.setBounds(26, 346, 165, 20);
        panel.add(emailField);
        emailField.setColumns(10);

        JLabel lblNewLabel_10 = new JLabel("Numéro Teléphone Fixe :");
        lblNewLabel_10.setBounds(246, 321, 165, 14);
        panel.add(lblNewLabel_10);

        JLabel lblNewLabel_11 = new JLabel("Numéro Téléphone Portable : ");
        lblNewLabel_11.setBounds(26, 389, 178, 14);
        panel.add(lblNewLabel_11);

        numFixeField = new JTextField();
        numFixeField.setBounds(246, 346, 165, 20);
        panel.add(numFixeField);
        numFixeField.setColumns(10);

        numPortableField = new JTextField();
        numPortableField.setBounds(26, 414, 165, 20);
        panel.add(numPortableField);
        numPortableField.setColumns(10);

        JLabel lblNewLabel_12 = new JLabel("Région :");
        lblNewLabel_12.setBounds(246, 387, 165, 14);
        panel.add(lblNewLabel_12);

        regionComboBox = new JComboBox<>();
        regionComboBox.setBounds(246, 412, 165, 22);
        panel.add(regionComboBox);

        JButton btnAjoutUtilisateur = new JButton("Ajouter Visiteur");
        btnAjoutUtilisateur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (areFieldsValid()) {
                    Date maDate = new Date();
                    Region maRegion = (Region) regionComboBox.getSelectedItem();
                    ArrayList<FicheFrais> mesFichesFrais = new ArrayList<>();
                    Utilisateur monUtilisateur = new Utilisateur(idVisiteurField.getText(), nomField.getText(), prenomField.getText(), loginField.getText(), passwordField.getText(), adresseField.getText(), codePostalField.getText(), villeField.getText(), maDate, emailField.getText(), numFixeField.getText(), numPortableField.getText(), maRegion, AccesData.getRoleById("v"), mesFichesFrais);
                    Boolean isSuccess = AccesData.insertionVisiteur(monUtilisateur);

                    if (isSuccess) {
                        JOptionPane.showMessageDialog(panel, "L'utilisateur " + monUtilisateur.getNom() + " " + monUtilisateur.getPrenom() + " a été créé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Fermer la fenêtre de création d'utilisateur
                    } else {
                        JOptionPane.showMessageDialog(panel, "Une erreur est survenue lors de la création de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnAjoutUtilisateur.setBounds(147, 549, 140, 34);
        panel.add(btnAjoutUtilisateur);

        remplirComboBoxRegions();

        setVisible(true);
    }

    private boolean areFieldsValid() {
        return !nomField.getText().isEmpty() && !prenomField.getText().isEmpty() && !loginField.getText().isEmpty() && !passwordField.getText().isEmpty()
                && !adresseField.getText().isEmpty() && !codePostalField.getText().isEmpty() && !villeField.getText().isEmpty()
                && !emailField.getText().isEmpty() && !numFixeField.getText().isEmpty() && !numPortableField.getText().isEmpty()
                && regionComboBox.getSelectedItem() != null;
    }

    private void remplirComboBoxRegions() {
        List<Region> regions = AccesData.getLesRegions();
        for (Region region : regions) {
            regionComboBox.addItem(region);
        }
        regionComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Region) {
                    setText(((Region) value).getLibelleRegion());
                }
                return this;
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        instance = null;
    }
}