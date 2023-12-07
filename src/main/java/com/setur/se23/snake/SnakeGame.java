package com.setur.se23.snake;

import java.util.ArrayList;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.FX_Input;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.loop.Loop;
import com.setur.se23.snake.Snake_Objects.Background;
import com.setur.se23.snake.Snake_Objects.SnakeBody;
import com.setur.se23.snake.Snake_Objects.SnakeHead;
import com.setur.se23.snake.Snake_Objects.SnakeTail;

public class SnakeGame {
    
    private Input inputSystem;

    private Grid grid;
    private SnakeHead snakeHead;
    private SnakeBody snakeBody;
    private SnakeTail snakeTail;

    // can be -1, 0 or 1.
    private int directionX = 1; // Initally moving right.
    private int directionY = 0; // Initially no up/down movement.
    
    private boolean appleEaten = false;

    public Loop gameLoop = new Loop();

    public SnakeGame() {
        this.grid = new Grid();
        initializeSnake();
        sendGameObjects();
    }

    private void initializeSnake() {
        // Middle of the grid
        int[] headPosition = {25, 24};
        int[] bodyPosition = {24, 24};
        int[] tailPosition = {23, 24};

        double[] headStageCoordinates = GridUtils.gridToStageCoordinates(headPosition[0], headPosition[1]);
        double[] bodyStageCoordinates = GridUtils.gridToStageCoordinates(bodyPosition[0], bodyPosition[1]);
        double[] tailStageCoordinates = GridUtils.gridToStageCoordinates(tailPosition[0], tailPosition[1]);

        snakeHead = new SnakeHead(headStageCoordinates[0], headStageCoordinates[1], 0);
        snakeBody = new SnakeBody(bodyStageCoordinates[0], bodyStageCoordinates[1], 0);
        snakeTail = new SnakeTail(tailStageCoordinates[0], tailStageCoordinates[1], 0);

        // Update grid status
        grid.setCell(headPosition[0], headPosition[1], true);
        grid.setCell(bodyPosition[0], bodyPosition[1], true);
        grid.setCell(tailPosition[0], tailPosition[1], true);
    }

    public void moveSnake() {
        // Determine new head position based on direction
        int[] newHeadPosition = calculateNewHeadPosition();
    
        // Check for collisions or apple consumption at the new head position
    
        // Move the snake
        updateSnakeBodyPositions(newHeadPosition);
    
        // Remove the tail segment if no apple was eaten
        if (!appleEaten) {
            removeTailSegment();
        }
    }

    
    private int[] calculateNewHeadPosition() {
        int currentX = (int)snakeHead.getX()/16;
        int currentY = (int)snakeHead.getY()/16;

        int newHeadX = currentX + directionX;
        int newHeadY = currentY + directionY;

        return new int[]{newHeadX, newHeadY};
    }

    private void updateSnakeBodyPositions(int[] newHeadPosition) {
        // Shift each body part to the position of the part in front of it
        // Set the new position for the head
        snakeHead.setPosition(newHeadPosition[0], newHeadPosition[1]);
        snakeTail.setPosition(newHeadPosition[0], newHeadPosition[1]);
        // Similar logic for other body parts
    }
    
    private void removeTailSegment() {
        // Remove the last segment of the snake's body
        // Update the grid and snake body array accordingly
    }

    private void sendGameObjects() {
        gameLoop.sendScene(createSnakeGameObjects(), getFunctions());
    }

    private ArrayList<Entity> createSnakeGameObjects() {
        ArrayList<Entity> entities = new ArrayList<Entity>();

        entities.add(new Background());

        entities.add(snakeHead);
        entities.add(snakeBody);
        entities.add(snakeTail);

        createInputs(snakeHead);

        return entities;
    }

        private void createInputs(SnakeHead player) {
        inputSystem = new FX_Input(new GameEvents(player, () -> sendGameObjects()));

        inputSystem.addInputs();
    }

    private ArrayList<Runnable> getFunctions() {
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();

        moveSnake();

        return runnables;
    }
}
