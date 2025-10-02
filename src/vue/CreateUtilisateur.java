package vue;

import javax.swing.*;

import metier.Region;
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
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        JLabel lblPrenom = new JLabel("Prénom : ");
        lblPrenom.setBounds(246, 34, 165, 14);
        panel.add(lblPrenom);

        prenomField = new JTextField();
        prenomField.setBounds(246, 59, 165, 20);
        panel.add(prenomField);
        prenomField.setColumns(10);

        JLabel lblLogin = new JLabel("Login : ");
        lblLogin.setBounds(246, 108, 165, 14);
        panel.add(lblLogin);

        JLabel lblMotDePasse = new JLabel("Mot de passe : ");
        lblMotDePasse.setBounds(26, 177, 165, 14);
        panel.add(lblMotDePasse);

        loginField = new JTextField();
        loginField.setBounds(246, 133, 165, 20);
        panel.add(loginField);
        loginField.setColumns(10);

        passwordField = new JTextField();
        passwordField.setBounds(26, 202, 165, 20);
        panel.add(passwordField);
        passwordField.setColumns(10);

        JLabel lblAdresse = new JLabel("Adresse : ");
        lblAdresse.setBounds(246, 244, 165, 14);
        panel.add(lblAdresse);

        JLabel lblCodePostal = new JLabel("Code Postal : ");
        lblCodePostal.setBounds(246, 177, 165, 14);
        panel.add(lblCodePostal);

        adresseField = new JTextField();
        adresseField.setBounds(246, 275, 165, 20);
        panel.add(adresseField);
        adresseField.setColumns(10);

        codePostalField = new JTextField();
        codePostalField.setBounds(246, 202, 165, 20);
        panel.add(codePostalField);
        codePostalField.setColumns(10);

        JLabel lblVille = new JLabel("Ville : ");
        lblVille.setBounds(26, 244, 165, 14);
        panel.add(lblVille);

        JLabel lblIdentifiantVisiteur = new JLabel("Identifiant visiteur");
        lblIdentifiantVisiteur.setBounds(26, 108, 165, 14);
        panel.add(lblIdentifiantVisiteur);

        villeField = new JTextField();
        villeField.setBounds(26, 275, 165, 20);
        panel.add(villeField);
        villeField.setColumns(10);

        idVisiteurField = new JTextField();
        idVisiteurField.setBounds(26, 133, 165, 20);
        panel.add(idVisiteurField);
        idVisiteurField.setColumns(10);

        JLabel lblAdresseEmail = new JLabel("Adresse Email : ");
        lblAdresseEmail.setBounds(26, 325, 165, 14);
        panel.add(lblAdresseEmail);

        emailField = new JTextField();
        emailField.setBounds(26, 346, 165, 20);
        panel.add(emailField);
        emailField.setColumns(10);

        JLabel lblNumeroTelephoneFixe = new JLabel("Numéro Teléphone Fixe :");
        lblNumeroTelephoneFixe.setBounds(246, 321, 165, 14);
        panel.add(lblNumeroTelephoneFixe);

        JLabel lblNumeroTelephonePortable = new JLabel("Numéro Téléphone Portable : ");
        lblNumeroTelephonePortable.setBounds(26, 389, 178, 14);
        panel.add(lblNumeroTelephonePortable);

        numFixeField = new JTextField();
        numFixeField.setBounds(246, 346, 165, 20);
        panel.add(numFixeField);
        numFixeField.setColumns(10);

        numPortableField = new JTextField();
        numPortableField.setBounds(26, 414, 165, 20);
        panel.add(numPortableField);
        numPortableField.setColumns(10);

        JLabel lblRegion = new JLabel("Région :");
        lblRegion.setBounds(246, 387, 165, 14);
        panel.add(lblRegion);

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