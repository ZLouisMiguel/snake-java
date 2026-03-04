package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {

    private final JLabel scoreLabel;
    private final JButton retryButton;
    private final JButton menuButton;

    public GameOverPanel(ActionListener retryListener, ActionListener menuListener) {
        setLayout(new GridBagLayout());
        setBackground(Color.black);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("GAME OVER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreLabel.setForeground(Color.white);
        gbc.gridy = 1;
        add(scoreLabel, gbc);

        retryButton = new JButton("Retry");
        retryButton.setFont(new Font("Arial", Font.PLAIN, 24));
        retryButton.addActionListener(retryListener);
        gbc.gridy = 2;
        add(retryButton, gbc);

        menuButton = new JButton("Main Menu");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 24));
        menuButton.addActionListener(menuListener);
        gbc.gridy = 3;
        add(menuButton, gbc);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}