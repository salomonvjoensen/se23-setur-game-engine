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
    private static final long MOVE_INTERVAL = 500;  // 500ms.
    private static final int S_C = 16;  // Size of each cell.
    
    private Input inputSystem;

    private Grid grid;
    ArrayList<SnakeEntity> snakeEntities;
    private Background background;
    private Apple apple;
    private SnakeHead snakeHead;
    private SnakeBody snakeBody1;
    private SnakeBody snakeBody2;
    private SnakeBody snakeBody3;
    private SnakeTail snakeTail;

    // can be -1, 0 or 1.
    public int directionX;
    public int directionY;
    
    private boolean appleEaten = false;

    public Loop gameLoop = new Loop();

    public SnakeGame() {
        this.background = new Background();
        this.apple = new Apple(Core.randomInt(0, 50), Core.randomInt(0, 50));
        this.grid = new Grid();
        // this.directionX = 1;  // Initally moving right.
        // this.directionY = 0;  // Initially no up/down movement.
        this.snakeEntities = new ArrayList<>();

        initializeSnake();        

        sendGameObjects();
    }

    private void sendGameObjects() {
        gameLoop.sendScene(createSnakeGameObjects(), getRunnables());

    }

    private void initializeSnake() {
        // Middle of the grid
        int[] headPosition = {26, 24};
        int[] bodyPosition1 = {25, 24};
        int[] bodyPosition2 = {24, 24};
        int[] bodyPosition3 = {23, 24};
        int[] tailPosition = {22, 24};

        double[] headStageCoordinates = GridUtils.gridToStageCoordinates(headPosition[0], headPosition[1]);
        double[] bodyStageCoordinates1 = GridUtils.gridToStageCoordinates(bodyPosition1[0], bodyPosition1[1]);
        double[] bodyStageCoordinates2 = GridUtils.gridToStageCoordinates(bodyPosition2[0], bodyPosition2[1]);
        double[] bodyStageCoordinates3 = GridUtils.gridToStageCoordinates(bodyPosition3[0], bodyPosition3[1]);
        double[] tailStageCoordinates = GridUtils.gridToStageCoordinates(tailPosition[0], tailPosition[1]);

        snakeHead = new SnakeHead(headStageCoordinates[0], headStageCoordinates[1], 0);
        snakeBody1 = new SnakeBody(bodyStageCoordinates1[0], bodyStageCoordinates1[1], 0);
        snakeBody2 = new SnakeBody(bodyStageCoordinates2[0], bodyStageCoordinates2[1], 0);
        snakeBody3 = new SnakeBody(bodyStageCoordinates3[0], bodyStageCoordinates3[1], 0);
        snakeTail = new SnakeTail(tailStageCoordinates[0], tailStageCoordinates[1], 0);

        snakeEntities.add(snakeTail);
        snakeEntities.add(snakeBody1);
        snakeEntities.add(snakeBody2);
        snakeEntities.add(snakeBody3);
        snakeEntities.add(snakeHead);

        // Update grid status
        grid.setCell(headPosition[0], headPosition[1], true);
        grid.setCell(bodyPosition1[0], bodyPosition1[1], true);
        grid.setCell(bodyPosition2[0], bodyPosition1[1], true);
        grid.setCell(bodyPosition3[0], bodyPosition1[1], true);
        grid.setCell(tailPosition[0], tailPosition[1], true);

    }

    public void moveSnake() {
        int[] newHeadPosition = calculateNewHeadPosition();
    
        // Check for collisions or apple consumption at the new head position
        // ...
    
        // Convert the old head into a body part
        SnakeHead oldHeadAsBody = new SnakeHead(snakeHead.getX(), snakeHead.getY(), snakeHead.getAngle());
        // snakeEntities.removeLast();
        snakeEntities.add(oldHeadAsBody);  // Add this new body part to the list

        // Update the position of the head
        snakeHead.setPosition(
            newHeadPosition[0],
            newHeadPosition[1],
            calculateHeadAngle()
        );

        // Update the positions of the rest of the snake
        updateSnakePositions();

        // Remove the tail segment if no apple was eaten
        if (!appleEaten) {
            removeTailSegment();
        }
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
        int currentX = (int)snakeHead.getX()/S_C;
        int currentY = (int)snakeHead.getY()/S_C;

        System.out.println("currentX: " + currentX + "  currentY: " + currentY + "  dirX: " + snakeHead.getDirectionX() + "  dirY: " + snakeHead.getDirectionY());

        int newHeadX = (int)snakeHead.getX()/S_C + snakeHead.getDirectionX();
        int newHeadY = (int)snakeHead.getY()/S_C + snakeHead.getDirectionY();

        return new int[]{newHeadX, newHeadY};
    }

    private double calculateHeadAngle() {
        if (snakeHead.getDirectionX() == 1) return 0;    // Right
        if (snakeHead.getDirectionX() == -1) return 180; // Left
        if (snakeHead.getDirectionY() == 1) return 90;   // Down
        if (snakeHead.getDirectionY() == -1) return 270; // Up
        return 0;
    }

    private void updateSnakePositions() {
        // Shift each body part to the position of the part in front of it
        // Set the new position for the head

        for (SnakeEntity entity : snakeEntities) {
            entity.setPosition(entity.getX()/S_C, entity.getY()/S_C, entity.getAngle());
        }
    }
    
    private void removeTailSegment() {
        // Remove the last segment of the snake's body
        // Update the grid and snake body array accordingly

        snakeEntities.remove(0); // Assuming the tail is always at index 0
            
        // Make last SnakeEntity to tail.
        SnakeEntity replaceBody = snakeEntities.get(0);

            // If the new tail is a body part, convert it to a tail
            SnakeTail newTail = new SnakeTail(replaceBody.getX(), replaceBody.getY(), replaceBody.getAngle());
    
            // Replace the body part with the new tail in the list
            snakeEntities.set(0, newTail);
    }

    private ArrayList<Entity> createSnakeGameObjects() {
        ArrayList<Entity> entities = new ArrayList<Entity>();

        entities.add(new Background());

        for (SnakeEntity snakeEntity : snakeEntities) {
            entities.add(snakeEntity);
        }

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
                refreshRenderingEntities();
                lastMoveTime = currentTime;
            }
        });

        return runnables;
    }
}
