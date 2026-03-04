import javax.swing.*;
import java.awt.*;

import panels.GameOverPanel;
import panels.GamePanel;
import panels.MenuPanel;


public class Main {

    private static final String PANEL_MENU = "Menu";
    private static final String PANEL_GAME = "Game";
    private static final String PANEL_GAME_OVER = "GameOver";

    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::buildAndShowUI);
    }

    private static void buildAndShowUI() {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel root = new JPanel(new CardLayout());

        MenuPanel menuPanel = new MenuPanel(e -> showPanel(root, PANEL_GAME), e -> System.exit(0));

        GamePanel gamePanel = new GamePanel(BOARD_WIDTH, BOARD_HEIGHT);
        GameOverPanel gameOverPanel = new GameOverPanel(
                e -> {
                    gamePanel.resetGame();
                    showPanel(root, PANEL_GAME);
                },
                e -> {
                    gamePanel.resetGame();
                    showPanel(root, PANEL_MENU);
                }
        );

        gamePanel.setGameOverListener(score -> {
            gameOverPanel.setScore(score);
            showPanel(root, PANEL_GAME_OVER);
        });

        root.add(menuPanel, PANEL_MENU);
        root.add(gamePanel, PANEL_GAME);
        root.add(gameOverPanel, PANEL_GAME_OVER);

        showPanel(root, PANEL_MENU);

        frame.add(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showPanel(JPanel root, String panelName) {
        CardLayout layout = (CardLayout) root.getLayout();
        layout.show(root, panelName);

        if (PANEL_GAME.equals(panelName)) {
            for (Component c : root.getComponents()) {
                if (c instanceof GamePanel) {
                    c.requestFocusInWindow();
                    break;
                }
            }
        }
    }
}