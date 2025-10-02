package vue;

import javax.swing.*;
import javax.swing.WindowConstants;
import listeners.LoginListener;
import metier.Utilisateur;
import persistance.AccesData;
import persistance.HibernateSession;
import utils.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Login extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JLabel messageLabel;
    private transient Utilisateur util;
    private transient List<LoginListener> listeners = new ArrayList<>();
    private transient LoadingScreen loadingScreen;

    public Login() {
        setTitle("Connexion");
        setSize(440, 280);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        
        ImageIcon originalIconLogo = new ImageIcon(Menu.class.getResource("/resources/GSB.png"));  
        setIconImage(originalIconLogo.getImage());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Utilisateur:"), gbc);
        userField = new JTextField();
        userField.setPreferredSize(new Dimension(250, 30));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(userField, gbc);

        gbc.gridy = 2;
        add(new JLabel("Mot de passe:"), gbc);
        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(250, 30));
        gbc.gridy = 3;
        add(passField, gbc);

        loginButton = new JButton("Se connecter");
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        loadingScreen = new LoadingScreen(this);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            loadingScreen.show();

            // Utiliser SwingWorker pour effectuer la tâche en arrière-plan
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // Initialiser la session Hibernate
                    HibernateSession.getSession();
                    util = AccesData.getUtilisateurByLoginAndMdp(username, password);
                    return null;
                }                    @Override
                    protected void done() {
                        try {
                            get();
                            loadingScreen.hide();

                            if (util != null) {
                                messageLabel.setText("Connexion réussie !");
                                messageLabel.setForeground(Color.GREEN);
                                notifyListeners(util);
                            } else {
                                messageLabel.setText("Identifiants incorrects.");
                                messageLabel.setForeground(Color.RED);
                            }
                        } catch (InterruptedException ex) {
                            // Re-interrompre le thread courant
                            Thread.currentThread().interrupt();
                            messageLabel.setText("Connexion interrompue.");
                            messageLabel.setForeground(Color.RED);
                            loadingScreen.hide();
                        } catch (Exception ex) {
                            messageLabel.setText("Erreur de connexion.");
                            messageLabel.setForeground(Color.RED);
                            loadingScreen.hide();
                            Logger.error("Erreur lors de la connexion", ex);
                        }
                    }
                };
                worker.execute();
        });
    }

    public void addLoginListener(LoginListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(Utilisateur utilisateur) {
        for (LoginListener listener : listeners) {
            listener.onLoginSuccess(utilisateur, this);
        }
    }
}