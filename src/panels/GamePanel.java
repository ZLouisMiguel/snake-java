package panels;

import utils.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    // Board

    private final int boardWidth, boardHeight, tileSize = 25;

    // Snake

    private Tile snakeHead;
    private final ArrayList<Tile> snakeBody = new ArrayList<>();

    // Food

    private Tile food;
    private final Random random = new Random();

    // Game logic

    private final Timer gameLoop;
    private int velocityX = 0, velocityY = 0;
    private boolean gameOver = false;

    public GamePanel(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        food = new Tile(10, 10);
        placeFood();

        gameLoop = new Timer(100, this);
        gameLoop.start();

    }

    // =========================
    // Utilities
    // =========================

    private boolean collision(Tile t1, Tile t2) {
        return t1.equals(t2);
    }

    private boolean validFoodPosition() {
        if (collision(food, snakeHead)) return false;
        for (Tile part : snakeBody) {
            if (collision(food, part)) return false;
        }
        return true;
    }

    private void placeFood() {
        do {
            food.x = random.nextInt(boardWidth / tileSize);
            food.y = random.nextInt(boardHeight / tileSize);
        } while (!validFoodPosition());
    }

    // =========================
    // Core game logic
    // =========================

    private void move() {
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Move body
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile part = snakeBody.get(i);
            if (i == 0) {
                part.x = snakeHead.x;
                part.y = snakeHead.y;
            } else {
                Tile prev = snakeBody.get(i - 1);
                part.x = prev.x;
                part.y = prev.y;
            }
        }

        // Move head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Check collisions
        for (Tile part : snakeBody) {
            if (collision(snakeHead, part)) triggerGameOver();
        }

        if (snakeHead.x < 0 || snakeHead.x >= boardWidth / tileSize ||
                snakeHead.y < 0 || snakeHead.y >= boardHeight / tileSize) {
            triggerGameOver();
        }
    }

    private void triggerGameOver() {
        if (!gameOver) gameOver = true;
    }

    private void resetGame() {
        snakeHead = new Tile(5, 5);
        snakeBody.clear();
        velocityX = 0;
        velocityY = 0;
        gameOver = false;
        placeFood();
    }

    // =========================
    // Rendering
    // =========================

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Food
        g.setColor(Color.red);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        // Snake head
        g.setColor(Color.decode("#345ff2"));
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        // Snake body
        g.setColor(Color.green);
        for (Tile part : snakeBody) {
            g.fill3DRect(part.x * tileSize, part.y * tileSize, tileSize, tileSize, true);
        }

        // Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over: " + snakeBody.size(), tileSize - 16, tileSize);
            g.drawString("Press SPACE to Restart", tileSize - 16, tileSize + 20);
        } else {
            g.setColor(Color.decode("#345ff2"));
            g.drawString("Score: " + snakeBody.size(), tileSize - 16, tileSize);
        }
    }

    // =========================
    // Game loop
    // =========================

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) move();
        repaint();
    }

    // =========================
    // Input
    // =========================

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) resetGame();
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                if (velocityY != 1) {
                    velocityX = 0;
                    velocityY = -1;
                }
            }
            case KeyEvent.VK_DOWN -> {
                if (velocityY != -1) {
                    velocityX = 0;
                    velocityY = 1;
                }
            }
            case KeyEvent.VK_LEFT -> {
                if (velocityX != 1) {
                    velocityX = -1;
                    velocityY = 0;
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (velocityX != -1) {
                    velocityX = 1;
                    velocityY = 0;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}