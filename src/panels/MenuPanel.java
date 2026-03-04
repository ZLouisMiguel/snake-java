package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    public MenuPanel(ActionListener onStart, ActionListener onExit) {
        setLayout(new GridBagLayout());
        setBackground(new Color(20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 40, 10, 40);

        JLabel title = new JLabel("SNAKE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 52));
        title.setForeground(new Color(52, 95, 242));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 40, 4, 40);
        add(title, gbc);

        JLabel subtitle = new JLabel("Use arrow keys to move", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 40, 24, 40);
        add(subtitle, gbc);

        // ── Buttons ───────────────────────────────────────────────────────
        gbc.insets = new Insets(8, 60, 8, 60);

        JButton startButton = createMenuButton("Start Game", new Color(52, 95, 242));
        startButton.addActionListener(onStart);
        gbc.gridy = 2;
        add(startButton, gbc);

        JButton exitButton = createMenuButton("Exit", new Color(180, 40, 40));
        exitButton.addActionListener(onExit);
        gbc.gridy = 3;
        add(exitButton, gbc);
    }

    private JButton createMenuButton(String text, Color bgColor) {
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