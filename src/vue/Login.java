package vue;

import javax.swing.*;
import listeners.LoginListener;
import metier.Utilisateur;
import persistance.AccesData;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Login extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JLabel messageLabel;
    private Utilisateur util;
    @SuppressWarnings("unused")
	private Boolean status;
    private List<LoginListener> listeners = new ArrayList<>();
    private LoadingScreen loadingScreen;

    public Login() {
        setTitle("Connexion");
        setSize(440, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                status = true;
                loadingScreen.show();

                // Utiliser SwingWorker pour effectuer la tâche en arrière-plan
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        util = AccesData.getUtilisateurByLoginAndMdp(username, password);
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            get();
                            status = false;
                            loadingScreen.hide();

                            if (util != null) {
                                messageLabel.setText("Connexion réussie !");
                                messageLabel.setForeground(Color.GREEN);
                                notifyListeners();
                            } else {
                                messageLabel.setText("Identifiants incorrects.");
                                messageLabel.setForeground(Color.RED);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                };
                worker.execute();
            }
        });
    }

    public void addLoginListener(LoginListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (LoginListener listener : listeners) {
            listener.onLoginSuccess(util);
        }
    }
}