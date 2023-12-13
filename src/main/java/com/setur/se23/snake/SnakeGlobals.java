package com.setur.se23.snake;

public class SnakeGlobals {
    public static final int SCREEN_SIZE = 800;  // Default 800
    public static final int GRID_SIZE = 25;  // Default 50x50 grid size.
    public static final long MOVE_INTERVAL = 200;  // 200ms between updates.
    public static final int C_S = SCREEN_SIZE/GRID_SIZE;  // Size of each cell. (default 16) Scales after GRID_SIZE
    public static final double SCALE = (double) C_S/160;  // Default 0.1;
    public static final double PIXEL_SIZE = (double)SCREEN_SIZE/800;  // Default 1. Scaling according to Defeault Screen size.
}