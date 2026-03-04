package utils;

public class Tile {
    public int x, y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Tile other) {
        return this.x == other.x && this.y == other.y;
    }
}