package com.setur.se23.snake;

/**
 * Translation between grid and Stage.
 */
public class GridUtils {
    private static final int CELL_SIZE = SnakeGlobals.C_S; // Default Each cell is 16x16 pixels

    /**
     * Static method for translation from grid to Stage.
     * 
     * @param gridX
     * @param gridY
     * @return The corresponding position on the Stage.
     */
    public static double[] gridToStageCoordinates(int gridX, int gridY) {
        double stageX = gridX * CELL_SIZE;
        double stageY = gridY * CELL_SIZE;
        return new double[]{stageX, stageY};
    }

    /**
     * Static method for translation of grid X-coordinate to Stage.
     * 
     * @param gridX
     * @return The corresponding horizontal position on the Stage.
     */
    public static double gridToStageCoordinateX(int gridX) {
        double stageX = gridX * CELL_SIZE;
        return stageX;
    }
    
    /**
     * Static method for translation of grid Y-coordinate to Stage.
     * 
     * @param gridY
     * @return The corresponding vertical position on the Stage.
     */    
    public static double gridToStageCoordinateY(int gridY) {
        double stageY = gridY * CELL_SIZE;
        return stageY;
    }
}

