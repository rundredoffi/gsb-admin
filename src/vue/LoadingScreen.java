package vue;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen {
    private JDialog dialog;
    private JProgressBar progressBar;

    public LoadingScreen(JFrame parent) {
        dialog = new JDialog(parent, "Chargement", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Utiliser un JPanel avec GridBagLayout pour centrer le JLabel et la barre de progression
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel loadingLabel = new JLabel("Chargement en cours, veuillez patienter...", SwingConstants.CENTER);
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loadingLabel, gbc);

        gbc.gridy = 1;
        panel.add(progressBar, gbc);

        dialog.add(panel);
    }

    public void show() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dialog.setVisible(true);
            }
        });
    }

    public void hide() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dialog.setVisible(false);
            }
        });
    }
}