package vue;

import javax.swing.*;

import metier.Utilisateur;
import persistance.AccesData;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewUser extends JDialog {

    // Variable statique pour suivre si la fenêtre est déjà ouverte
    private static NewUser instance = null;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;

    public static void ouvrirFenetre(JFrame parent) {
        if (instance == null || !instance.isVisible()) {
            instance = new NewUser(parent);
        } else {
            // Optionnel : Ramener la fenêtre existante au premier plan
            instance.toFront();
        }
    }


    private NewUser(JFrame parent) {
    	
    	
        super(parent, "Nouvelle Fenêtre", true); // Le troisième paramètre 'true' active la modalité
        setSize(450, 650);
        setLocationRelativeTo(parent); // Centrer par rapport au parent
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Contenu de la fenêtre (inchangé)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 300);
        getContentPane().add(panel);
        
        textField = new JTextField();
        textField.setBounds(26, 59, 165, 20);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Nom : ");
        lblNewLabel.setBounds(26, 34, 165, 14);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Prénom : ");
        lblNewLabel_1.setBounds(246, 34, 165, 14);
        panel.add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        textField_1.setBounds(246, 59, 165, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Login : ");
        lblNewLabel_2.setBounds(26, 107, 165, 14);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Mot de passe : ");
        lblNewLabel_3.setBounds(246, 107, 165, 14);
        panel.add(lblNewLabel_3);
        
        textField_2 = new JTextField();
        textField_2.setBounds(26, 132, 165, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setBounds(246, 132, 165, 20);
        panel.add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Adresse : ");
        lblNewLabel_4.setBounds(26, 177, 165, 14);
        panel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Code Postal : ");
        lblNewLabel_5.setBounds(246, 177, 165, 14);
        panel.add(lblNewLabel_5);
        
        textField_4 = new JTextField();
        textField_4.setBounds(26, 202, 165, 20);
        panel.add(textField_4);
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setBounds(246, 202, 165, 20);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Ville : ");
        lblNewLabel_6.setBounds(26, 244, 165, 14);
        panel.add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Date embauche : ");
        lblNewLabel_7.setBounds(246, 244, 165, 14);
        panel.add(lblNewLabel_7);
        
        textField_6 = new JTextField();
        textField_6.setBounds(26, 275, 165, 20);
        panel.add(textField_6);
        textField_6.setColumns(10);
        
        textField_7 = new JTextField();
        textField_7.setBounds(246, 275, 165, 20);
        panel.add(textField_7);
        textField_7.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("ID Role : ");
        lblNewLabel_8.setBounds(26, 315, 165, 14);
        panel.add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Adresse Email : ");
        lblNewLabel_9.setBounds(246, 315, 165, 14);
        panel.add(lblNewLabel_9);
        
        textField_8 = new JTextField();
        textField_8.setBounds(26, 336, 165, 20);
        panel.add(textField_8);
        textField_8.setColumns(10);
        
        textField_9 = new JTextField();
        textField_9.setBounds(246, 336, 165, 20);
        panel.add(textField_9);
        textField_9.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Numéro Teléphone Fixe :");
        lblNewLabel_10.setBounds(26, 377, 165, 14);
        panel.add(lblNewLabel_10);
        
        JLabel lblNewLabel_11 = new JLabel("Numéro Téléphone Portable : ");
        lblNewLabel_11.setBounds(246, 377, 178, 14);
        panel.add(lblNewLabel_11);
        
        textField_10 = new JTextField();
        textField_10.setBounds(26, 402, 165, 20);
        panel.add(textField_10);
        textField_10.setColumns(10);
        
        textField_11 = new JTextField();
        textField_11.setBounds(246, 402, 165, 20);
        panel.add(textField_11);
        textField_11.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("ID Région : ");
        lblNewLabel_12.setBounds(26, 448, 165, 14);
        panel.add(lblNewLabel_12);
        
        textField_12 = new JTextField();
        textField_12.setBounds(26, 481, 165, 20);
        panel.add(textField_12);
        textField_12.setColumns(10);
        
        JButton btnNewButton = new JButton("Ajouter Visiteur");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton.setBounds(147, 549, 140, 34);
        panel.add(btnNewButton);

        
        
        setVisible(true);
    }


    @Override
    public void dispose() {
        super.dispose();
        instance = null;  // Réinitialiser l'instance lorsque la fenêtre est fermée
    }
}