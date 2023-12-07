package com.setur.se23.snake;

public class GridUtils {
    private static final int CELL_SIZE = 16; // Each cell is 16x16 pixels

    public static double[] gridToStageCoordinates(int gridX, int gridY) {
        double stageX = gridX * CELL_SIZE;
        double stageY = gridY * CELL_SIZE;
        return new double[]{stageX, stageY};
    }
}

