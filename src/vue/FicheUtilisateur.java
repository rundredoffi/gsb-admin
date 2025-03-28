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

public class FicheUtilisateur extends JDialog {

    private static FicheUtilisateur instance = null;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField loginField;
    private JTextField adresseField;
    private JTextField codePostalField;
    private JTextField villeField;
    private JTextField idVisiteurField;
    private JTextField emailField;
    private JTextField numFixeField;
    private JTextField numPortableField;
    private JTextField RoleField;
    private JTextField RegionField;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;

    public static void ouvrirFenetre(JFrame parent, String firstCellValue) {
        if (instance == null || !instance.isVisible()) {
            instance = new FicheUtilisateur(parent, firstCellValue);
        } else {
            instance.toFront();
        }
    }

    private FicheUtilisateur(JFrame parent,String firstCellValue) {
        super(parent, "Nouvelle Fenêtre", true);
        setSize(450, 650);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 300);
        getContentPane().add(panel);
        
        Utilisateur util = AccesData.getUtilisateurByID(firstCellValue);

        nomField = new JTextField(util.getNom());
        nomField.setEditable(false);
        nomField.setBounds(26, 59, 165, 20);
        panel.add(nomField);
        nomField.setColumns(10);

        prenomField = new JTextField(util.getPrenom());
        prenomField.setEditable(false);
        prenomField.setBounds(246, 59, 165, 20);
        panel.add(prenomField);
        prenomField.setColumns(10);

        loginField = new JTextField(util.getLogin());
        loginField.setEditable(false);
        loginField.setBounds(246, 133, 165, 20);
        panel.add(loginField);
        loginField.setColumns(10);

        adresseField = new JTextField(util.getAdresse());
        adresseField.setEditable(false);
        adresseField.setBounds(246, 204, 165, 20);
        panel.add(adresseField);
        adresseField.setColumns(10);

        codePostalField = new JTextField(util.getCp());
        codePostalField.setEditable(false);
        codePostalField.setBounds(26, 204, 165, 20);
        panel.add(codePostalField);
        codePostalField.setColumns(10);

        villeField = new JTextField(util.getVille());
        villeField.setEditable(false);
        villeField.setBounds(26, 266, 165, 20);
        panel.add(villeField);
        villeField.setColumns(10);

        idVisiteurField = new JTextField(util.getIdUtilisateur());
        idVisiteurField.setEditable(false);
        idVisiteurField.setBounds(26, 133, 165, 20);
        panel.add(idVisiteurField);
        idVisiteurField.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("Rôle :");
        lblNewLabel_8.setBounds(26, 306, 165, 14);
        panel.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("Adresse Email : ");
        lblNewLabel_9.setBounds(246, 245, 165, 14);
        panel.add(lblNewLabel_9);

        emailField = new JTextField(util.getEmail());
        emailField.setEditable(false);
        emailField.setBounds(246, 266, 165, 20);
        panel.add(emailField);
        emailField.setColumns(10);

        JLabel lblNewLabel_10 = new JLabel("Numéro Teléphone Fixe :");
        lblNewLabel_10.setBounds(26, 377, 165, 14);
        panel.add(lblNewLabel_10);

        JLabel lblNewLabel_11 = new JLabel("Numéro Téléphone Portable : ");
        lblNewLabel_11.setBounds(246, 306, 178, 14);
        panel.add(lblNewLabel_11);

        numFixeField = new JTextField(util.getTelfixe());
        numFixeField.setEditable(false);
        numFixeField.setBounds(26, 402, 165, 20);
        panel.add(numFixeField);
        numFixeField.setColumns(10);

        numPortableField = new JTextField(util.getTelPortable());
        numPortableField.setEditable(false);
        numPortableField.setBounds(246, 331, 165, 20);
        panel.add(numPortableField);
        numPortableField.setColumns(10);

        JLabel lblNewLabel_12 = new JLabel("Région :");
        lblNewLabel_12.setBounds(246, 377, 165, 14);
        panel.add(lblNewLabel_12);

        
        
        RoleField = new JTextField(util.getRole().getLibelleRole());
        RoleField.setEditable(false);
        RoleField.setColumns(10);
        RoleField.setBounds(26, 331, 165, 20);
        panel.add(RoleField);
        
        RegionField = new JTextField(util.getRegion().getLibelleRegion());
        RegionField.setEditable(false);
        RegionField.setColumns(10);
        RegionField.setBounds(246, 402, 165, 20);
        panel.add(RegionField);
        
        lblNewLabel = new JLabel("Ville :");
        lblNewLabel.setBounds(26, 241, 165, 14);
        panel.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Adresse :");
        lblNewLabel_1.setBounds(246, 179, 165, 14);
        panel.add(lblNewLabel_1);
        
        lblNewLabel_3 = new JLabel("Code Postal :");
        lblNewLabel_3.setBounds(26, 179, 165, 14);
        panel.add(lblNewLabel_3);
        
        lblNewLabel_4 = new JLabel("Identifiant visiteur :");
        lblNewLabel_4.setBounds(26, 108, 165, 14);
        panel.add(lblNewLabel_4);
        
        lblNewLabel_5 = new JLabel("Login :");
        lblNewLabel_5.setBounds(246, 108, 165, 14);
        panel.add(lblNewLabel_5);
        
        lblNewLabel_6 = new JLabel("Nom  :");
        lblNewLabel_6.setBounds(26, 34, 165, 14);
        panel.add(lblNewLabel_6);
        
        lblNewLabel_7 = new JLabel("Prénom :");
        lblNewLabel_7.setBounds(246, 34, 165, 14);
        panel.add(lblNewLabel_7);

       

        setVisible(true);
    }

    

    @Override
    public void dispose() {
        super.dispose();
        instance = null;
    }
}