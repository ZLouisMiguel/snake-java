package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {

    private final JLabel scoreLabel;

    public GameOverPanel(ActionListener onRetry, ActionListener onMenu) {
        setLayout(new GridBagLayout());
        setBackground(new Color(10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 60, 10, 60);

        JLabel title = new JLabel("GAME OVER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(new Color(200, 40, 40));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 60, 6, 60);
        add(title, gbc);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        scoreLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 60, 28, 60);
        add(scoreLabel, gbc);


        gbc.insets = new Insets(8, 60, 8, 60);

        JButton retryButton = createButton("Play Again", new Color(52, 95, 242));
        retryButton.addActionListener(onRetry);
        gbc.gridy = 2;
        add(retryButton, gbc);

        JButton menuButton = createButton("Main Menu", new Color(70, 70, 70));
        menuButton.addActionListener(onMenu);
        gbc.gridy = 3;
        add(menuButton, gbc);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        return btn;
    }
}