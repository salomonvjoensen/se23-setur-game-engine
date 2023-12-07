package com.setur.se23.snake;

public class Grid {
    private boolean[][] grid;
    private final int size = 50;

    public Grid() {
        grid = new boolean[size][size];
        // Initialize the grid with false (no snake parts in any cell initially)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void setCell(int x, int y, boolean containsSnake) {
        if (isValidCoordinate(x, y)) {
            grid[x][y] = containsSnake;
        }
    }

    public boolean getCell(int x, int y) {
        return isValidCoordinate(x, y) ? grid[x][y] : false;
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    // Any other grid-related methods can be added here
}
