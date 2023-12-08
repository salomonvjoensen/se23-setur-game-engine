package com.setur.se23.snake;

import java.util.ArrayList;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.input.FX_Input;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.input.InputEvents;
import com.setur.se23.engine.input.InputType;
import com.setur.se23.engine.loop.Loop;
import com.setur.se23.snake.Snake_Objects.Apple;
import com.setur.se23.snake.Snake_Objects.Background;
import com.setur.se23.snake.Snake_Objects.SnakeBody;
import com.setur.se23.snake.Snake_Objects.SnakeEntity;
import com.setur.se23.snake.Snake_Objects.SnakeHead;
import com.setur.se23.snake.Snake_Objects.SnakeTail;

import javafx.scene.input.KeyCode;

public class SnakeGame {

    private long lastMoveTime = System.currentTimeMillis();
    private static final long MOVE_INTERVAL = 200; // 200ms
    
    private Input inputSystem;

    private Grid grid;
    ArrayList<SnakeEntity> snakeEntities;
    private Background background;
    private Apple apple;
    private SnakeHead snakeHead;
    private SnakeBody snakeBody;
    private SnakeTail snakeTail;

    // can be -1, 0 or 1.
    private int directionX = 1; // Initally moving right.
    private int directionY = 0; // Initially no up/down movement.
    
    private boolean appleEaten = false;

    public Loop gameLoop = new Loop();

    public SnakeGame() {
        this.background = new Background();
        this.apple = new Apple(Core.randomInt(0, 50), Core.randomInt(0, 50));
        this.grid = new Grid();
        this.snakeEntities = new ArrayList<>();

        initializeSnake();        

        sendGameObjects();

    }

    private void sendGameObjects() {
        gameLoop.sendScene(createSnakeGameObjects(), getRunnables());

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

        snakeEntities.add(snakeTail);
        snakeEntities.add(snakeBody);
        snakeEntities.add(snakeHead);

        // Update grid status
        grid.setCell(headPosition[0], headPosition[1], true);
        grid.setCell(bodyPosition[0], bodyPosition[1], true);
        grid.setCell(tailPosition[0], tailPosition[1], true);
    }

    public void moveSnake() {
        int[] newHeadPosition = calculateNewHeadPosition();
    
        // Check for collisions or apple consumption at the new head position
        // ...
    
        // Create a new head at the new position
        SnakeHead newHead = new SnakeHead(
            GridUtils.gridToStageCoordinateX(newHeadPosition[0]), 
            GridUtils.gridToStageCoordinateY(newHeadPosition[1]),
            calculateHeadAngle());
        snakeEntities.add(newHead);

        System.out.println("x: " + newHead.getX() + "  y: " + newHead.getY());
        // Replace the old head with a body part
        snakeBody = new SnakeBody(snakeHead.getX(), snakeHead.getY(), snakeHead.getAngle());
        snakeEntities.add(snakeBody);

        updateSnakePositions();
    
        // Update the reference to the head
        snakeHead = newHead;
    
        // Remove the tail segment if no apple was eaten
        if (!appleEaten) {
            removeTailSegment();
        }

        refreshRenderingEntities();
    }

    private void refreshRenderingEntities() {
        ArrayList<Entity> entities = new ArrayList<Entity>();

        entities.add(background);
        entities.add(apple);

        for (SnakeEntity entity : snakeEntities) {
            entities.add(entity);
        }

        gameLoop.sendScene(entities, getRunnables());
    }

    private int[] calculateNewHeadPosition() {
        int currentX = (int)snakeHead.getX()/16;
        int currentY = (int)snakeHead.getY()/16;

        int newHeadX = currentX + directionX;
        int newHeadY = currentY + directionY;

        return new int[]{newHeadX, newHeadY};
    }

    private double calculateHeadAngle() {
        if (directionX == 1) return 0;    // Right
        if (directionX == -1) return 180; // Left
        if (directionY == 1) return 90;   // Down
        if (directionY == -1) return 270; // Up
        return 0;
    }

    private void updateSnakePositions() {
        // Shift each body part to the position of the part in front of it
        // Set the new position for the head

        for (SnakeEntity entity : snakeEntities) {
            entity.setPosition(entity.getX(), entity.getY(), entity.getAngle());
        }
        // snakeTail.setPosition(newX, newY);
        // Similar logic for other body parts
    }
    
    private void removeTailSegment() {
        // Remove the last segment of the snake's body
        // Update the grid and snake body array accordingly
    }

    private ArrayList<Entity> createSnakeGameObjects() {
        ArrayList<Entity> entities = new ArrayList<Entity>();

        entities.add(new Background());

        entities.add(snakeHead);
        entities.add(snakeBody);
        entities.add(snakeTail);

        // entities.add(snakeEntities);

        createInputs(snakeHead);

        return entities;
    }

    private void createInputs(SnakeHead player) {
        InputEvents gameEvents = new GameEvents(player, () -> sendGameObjects());

        FX_Input inputSystem = new FX_Input(gameEvents);

        inputSystem.addInput(InputType.onPress, KeyCode.P, "toggle_FPS_Counter");
        inputSystem.addInput(InputType.onPress, KeyCode.O, "toggle_Gizmos");
        inputSystem.addInput(InputType.onPress, KeyCode.R, "Restart");

        // Onkey press.
        inputSystem.addInput(InputType.onPress, KeyCode.W, "Up");
        inputSystem.addInput(InputType.onPress, KeyCode.S, "Down");
        inputSystem.addInput(InputType.onPress, KeyCode.A, "Left");
        inputSystem.addInput(InputType.onPress, KeyCode.D, "Right");

        // Onkey release.
        inputSystem.addInput(InputType.onRelease, KeyCode.W, "Up_Ready");
        inputSystem.addInput(InputType.onRelease, KeyCode.S, "Down_Ready");
        inputSystem.addInput(InputType.onRelease, KeyCode.A, "Left_Ready");
        inputSystem.addInput(InputType.onRelease, KeyCode.D, "Right_Ready");

        inputSystem.setInputs();
    }

    private ArrayList<Runnable> getRunnables() {
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();

        runnables.add(() -> {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastMoveTime >= MOVE_INTERVAL) {
                moveSnake();
                lastMoveTime = currentTime;
            }
        });

        return runnables;
    }
}
