package vue;

import javax.swing.*;

import metier.Utilisateur;
import persistance.AccesData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JLabel messageLabel;

    public Login() {
        setTitle("Connexion");
        setSize(440, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // Utilisation de GridBagLayout pour un placement flexible

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Marge autour des composants
        gbc.fill = GridBagConstraints.HORIZONTAL; // Pour forcer les composants à s'étirer horizontalement

        // Label et champ utilisateur
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Utilisateur:"), gbc);  // Texte au-dessus du champ utilisateur
        userField = new JTextField();
        userField.setPreferredSize(new Dimension(250, 30)); // Fixer la taille du champ utilisateur
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Pour étirer le champ utilisateur sur deux colonnes
        add(userField, gbc);

        // Label et champ mot de passe
        gbc.gridy = 2;
        add(new JLabel("Mot de passe:"), gbc);  // Texte au-dessus du champ mot de passe
        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(250, 30)); // Fixer la taille du champ mot de passe
        gbc.gridy = 3;
        add(passField, gbc);

        // Bouton de connexion
        loginButton = new JButton("Se connecter");
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Pour étirer le bouton sur deux colonnes
        add(loginButton, gbc);

        // Message de confirmation ou d'erreur
        messageLabel = new JLabel("", SwingConstants.CENTER);
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Pour étirer le message sur deux colonnes
        add(messageLabel, gbc);

        // Action du bouton de connexion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                
                Utilisateur util = AccesData.getUtilisateurByLoginAndMdp(username, password);

                Utilisateur util = AccesData.getUtilisateurByLoginAndMdp(username, password);

                if (util != null) {
                    messageLabel.setText("Connexion réussie !");
                    messageLabel.setForeground(Color.GREEN);
                } else {
                    messageLabel.setText("Identifiants incorrects.");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });
    }
    public static void main(String[] args) {
        // Lancer l'application
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
