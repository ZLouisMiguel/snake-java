import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private static class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    // snake vars
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    // food vars
    Tile food;
    Random random;

    // game logic vars
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    // =========================
    // Constructor
    // =========================
    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<>();

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    // =========================
    // Lowest-Level Utility
    // =========================
    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    // =========================
    // Food Validation (depends on collision)
    // =========================
    public boolean validFoodPosition() {
        if (collision(food, snakeHead)) return false;

        for (Tile snakePart : snakeBody) {
            if (collision(food, snakePart)) return false;
        }

        return true;
    }

    // =========================
    // Food Placement (depends on validFoodPosition)
    // =========================
    public void placeFood() {
        do {
            food.x = random.nextInt(boardWidth / tileSize);
            food.y = random.nextInt(boardHeight / tileSize);
        } while (!validFoodPosition());
    }

    // =========================
    // Core Game Logic (depends on collision + placeFood)
    // =========================
    public void move() {

        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Move body
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);

            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Move head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Self collision
        for (Tile snakePart : snakeBody) {
            if (collision(snakeHead, snakePart)) {
                triggerGameOver();
            }
        }

        // Wall collision
        if (snakeHead.x < 0 || snakeHead.x >= boardWidth / tileSize || snakeHead.y < 0 || snakeHead.y >= boardHeight / tileSize) {
            triggerGameOver();
        }
    }

    // =========================
    // Game Loop Entry (depends on move)
    // =========================
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
        }

        repaint();

    }

    // =========================
    // Rendering
    // =========================
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        g.setColor(Color.red);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        g.setColor(Color.decode("#345ff2"));
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        g.setColor(Color.green);
        for (Tile snakePart : snakeBody) {
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        g.setFont(new Font("Arial", Font.PLAIN, 16));

        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over: " + snakeBody.size(), tileSize - 16, tileSize);
        } else {
            g.setColor(Color.decode("#345ff2"));
            g.drawString("Score: " + snakeBody.size(), tileSize - 16, tileSize);
        }
    }

    // =========================
    // Input Handling
    // =========================
    @Override
    public void keyPressed(KeyEvent e) {

        if (gameOver) return;

        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    //state transition method

    private void triggerGameOver() {
        if (!gameOver) {
            gameOver = true;
        }
    }

    //Game reset method for internal game variables
    private void resetGame() {
        snakeHead = new Tile(5, 5);
        snakeBody.clear();

        velocityX = 0;
        velocityY = 0;

        gameOver = false;

        placeFood();
    }
}