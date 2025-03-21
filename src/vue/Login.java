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
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        userField = new JTextField(15);
        passField = new JPasswordField(15);
        loginButton = new JButton("Se connecter");
        messageLabel = new JLabel("", SwingConstants.CENTER);

        add(new JLabel("Utilisateur:"));
        add(userField);
        add(new JLabel("Mot de passe:"));
        add(passField);
        add(loginButton);
        add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                
                Utilisateur util = AccesData.getUtilisateurByLoginAndMdp(username, password);


                if (util != null) {
                    messageLabel.setText("Connexion réussie !");
                    messageLabel.setForeground(Color.GREEN);
                    setVisible(false);
            		new menu(util);

                } else {
                    messageLabel.setText("Identifiants incorrects.");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });
    }
}

   
        
    

