package vue;

import javax.swing.*;
import metier.Utilisateur;
import persistance.AccesData;

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
    private JTextField roleField;
    private JTextField regionField;
    private JLabel lblVille;
    private JLabel lblAdresse;
    private JLabel lblCodePostal;
    private JLabel lblIdentifiantVisiteur;
    private JLabel lblLogin;
    private JLabel lblNom;
    private JLabel lblPrenom;

    public static void ouvrirFenetre(JFrame parent, String firstCellValue) {
        if (instance == null || !instance.isVisible()) {
            instance = new FicheUtilisateur(parent, firstCellValue);
        } else {
            instance.toFront();
        }
    }

    private FicheUtilisateur(JFrame parent,String firstCellValue) {
        super(parent, "Fiche utilisateur : " + firstCellValue, true);
        setSize(450, 650);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        JLabel lblRole = new JLabel("Rôle :");
        lblRole.setBounds(26, 306, 165, 14);
        panel.add(lblRole);

        JLabel lblAdresseEmail = new JLabel("Adresse Email : ");
        lblAdresseEmail.setBounds(246, 245, 165, 14);
        panel.add(lblAdresseEmail);

        emailField = new JTextField(util.getEmail());
        emailField.setEditable(false);
        emailField.setBounds(246, 266, 165, 20);
        panel.add(emailField);
        emailField.setColumns(10);

        JLabel lblNumeroTelephoneFixe = new JLabel("Numéro Teléphone Fixe :");
        lblNumeroTelephoneFixe.setBounds(26, 377, 165, 14);
        panel.add(lblNumeroTelephoneFixe);

        JLabel lblNumeroTelephonePortable = new JLabel("Numéro Téléphone Portable : ");
        lblNumeroTelephonePortable.setBounds(246, 306, 178, 14);
        panel.add(lblNumeroTelephonePortable);

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

        JLabel lblRegion = new JLabel("Région :");
        lblRegion.setBounds(246, 377, 165, 14);
        panel.add(lblRegion);

        
        
        roleField = new JTextField(util.getRole().getLibelleRole());
        roleField.setEditable(false);
        roleField.setColumns(10);
        roleField.setBounds(26, 331, 165, 20);
        panel.add(roleField);
        
        regionField = new JTextField(util.getRegion().getLibelleRegion());
        regionField.setEditable(false);
        regionField.setColumns(10);
        regionField.setBounds(246, 402, 165, 20);
        panel.add(regionField);
        
        lblVille = new JLabel("Ville :");
        lblVille.setBounds(26, 241, 165, 14);
        panel.add(lblVille);
        
        lblAdresse = new JLabel("Adresse :");
        lblAdresse.setBounds(246, 179, 165, 14);
        panel.add(lblAdresse);
        
        lblCodePostal = new JLabel("Code Postal :");
        lblCodePostal.setBounds(26, 179, 165, 14);
        panel.add(lblCodePostal);
        
        lblIdentifiantVisiteur = new JLabel("Identifiant visiteur :");
        lblIdentifiantVisiteur.setBounds(26, 108, 165, 14);
        panel.add(lblIdentifiantVisiteur);
        
        lblLogin = new JLabel("Login :");
        lblLogin.setBounds(246, 108, 165, 14);
        panel.add(lblLogin);
        
        lblNom = new JLabel("Nom  :");
        lblNom.setBounds(26, 34, 165, 14);
        panel.add(lblNom);
        
        lblPrenom = new JLabel("Prénom :");
        lblPrenom.setBounds(246, 34, 165, 14);
        panel.add(lblPrenom);

       

        setVisible(true);
    }

    

    @Override
    public void dispose() {
        super.dispose();
        instance = null; // NOSONAR - Pattern singleton: reset de l'instance statique lors de la fermeture
    }
}