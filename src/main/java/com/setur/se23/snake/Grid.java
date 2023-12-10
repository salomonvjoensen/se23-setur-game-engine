package com.setur.se23.snake;

/**
 * The logical grid used in the SnakeGame
 */
public class Grid {
    private boolean[][] grid;
    private int sizeX;  // Rows.
    private int sizeY;  // Columns.

    /**
     * Constructor, creates a 50x50 grid.
     * 
     */
    public Grid(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        grid = new boolean[sizeX][sizeY];
        // Initialize the grid with false (no snake parts in any cell initially)
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                grid[i][j] = false;
            }
        }
    }

    public int getX() {
        return sizeX;
    }

    public int getY() {
        return sizeY;
    }

    /**
     * Set method for if a cell contains a snake bodypart.
     * 
     * Not used, but would be faster to implement a boolean check on the grid
     * than collision between itself and an Apple using a logical grid.
     * 
     * @param x
     * @param y
     * @param containsSnake
     */
    public void setCell(int x, int y, boolean containsSnake) {
        if (isValidCoordinate(x, y)) {
            grid[x][y] = containsSnake;
        }
    }

    /**
     * Get method for boolean check for valid coordinate of the grid.
     * 
     * @param x
     * @param y
     * @return Check if the cell is valid.
     */
    public boolean getCell(int x, int y) {
        return isValidCoordinate(x, y) ? grid[x][y] : false;
    }

    /**
     * Get method for checking if the coordinate is within grid bounds.
     * 
     * @param x
     * @param y
     * @return Check if it's within grid bounds.
     */
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < sizeX && y >= 0 && y < sizeY;
    }
}
