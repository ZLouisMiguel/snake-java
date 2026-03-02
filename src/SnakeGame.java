import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel {

    private class Tile {
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

    //snake vars
    Tile snakeHead;

    //food vars
    Tile food;
    Random random;


    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);

        snakeHead = new Tile(5, 5);
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        //grid
        for (int i = 0; i < (boardWidth / tileSize); i++) {
            //(x1,y1,x2,y2);
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); //vertical lines
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize); // horizontal lines

        }

        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize , tileSize, tileSize);

        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);

    }
}
