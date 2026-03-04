package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private final JButton startButton;
    private final JButton exitButton;

    public MenuPanel(ActionListener startListener, ActionListener exitListener) {
        setLayout(new GridBagLayout());
        setBackground(Color.darkGray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("SNAKE GAME", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.addActionListener(startListener);
        gbc.gridy = 1;
        add(startButton, gbc);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        exitButton.addActionListener(exitListener);
        gbc.gridy = 2;
        add(exitButton, gbc);
    }
}