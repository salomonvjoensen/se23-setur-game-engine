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
import com.setur.se23.snake.Snake_Objects.GameOver;
import com.setur.se23.snake.Snake_Objects.SnakeBody;
import com.setur.se23.snake.Snake_Objects.SnakeBodyCounterClockwise;
import com.setur.se23.snake.Snake_Objects.SnakeBodyClockwise;
import com.setur.se23.snake.Snake_Objects.SnakeEntity;
import com.setur.se23.snake.Snake_Objects.SnakeHead;
import com.setur.se23.snake.Snake_Objects.SnakeTail;
import com.setur.se23.snake.Snake_Objects.StartGameInfo;

import javafx.scene.input.KeyCode;

public class SnakeGame {

    private long lastMoveTime = System.currentTimeMillis();
    private static final long MOVE_INTERVAL = 200;  // 200ms.
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
    private StartGameInfo startGameInfo;
    private GameOver gameOver;

    // can be -1, 0 or 1.
    public int directionX;
    public int directionY;
    
    private boolean isFirstMove;

    public Loop gameLoop = new Loop();

    public SnakeGame() {
        this.background = new Background();
        this.apple = new Apple(Core.randomInt(0, 784), Core.randomInt(0, 784));
        this.grid = new Grid();
        this.startGameInfo = new StartGameInfo();
        this.gameOver = new GameOver();
        this.snakeEntities = new ArrayList<SnakeEntity>();
        this.isFirstMove = true;

        initSnakeAndObjects();
    }

    public void initSnakeAndObjects() {
        // can be between 4-49 and 0-49
        initializeSnake(24, 24);        

        sendGameObjects();
    }

    private void sendGameObjects() {
        gameLoop.sendScene(createSnakeGameObjects(), getRunnables());

    }

    private void initializeSnake(int posX, int posY) {
        snakeEntities = new ArrayList<SnakeEntity>();

        // placement of snake.
        int[] headPosition = {posX, posY};
        int[] bodyPosition1 = {posX-1, posY};
        int[] bodyPosition2 = {posX-2, posY};
        int[] bodyPosition3 = {posX-3, posY};
        int[] tailPosition = {posX-4, posY};

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

        snakeEntities.addLast(snakeHead);
        snakeEntities.addLast(snakeBody1);
        snakeEntities.addLast(snakeBody2);
        snakeEntities.addLast(snakeBody3);
        snakeEntities.addLast(snakeTail);

        // Update grid status
        grid.setCell(headPosition[0], headPosition[1], true);
        grid.setCell(bodyPosition1[0], bodyPosition1[1], true);
        grid.setCell(bodyPosition2[0], bodyPosition1[1], true);
        grid.setCell(bodyPosition3[0], bodyPosition1[1], true);
        grid.setCell(tailPosition[0], tailPosition[1], true);

    }

    public void moveSnake() {
        if (snakeHead.isAlive() && !isFirstMove) {
        int[] newHeadPosition = calculateNewHeadPosition();

        // Convert the old head into a body part, defaults to SnakeBody.
        SnakeEntity oldHeadAsBody = new SnakeBody(snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
        
        // Before updating the head's position, check if the direction has changed
        // Replace the body part behind the head with the appropriate bendy part
        // Determine the appropriate bendy part based on direction, --8-- possibilities, with 4 different conditions.
        if (snakeHead.isDirectionChanged()) {
            // moving down to right -or- moving up to left (i.e. counterclockwise).
            if ((snakeHead.getPrevDirectionY() == 1 && snakeHead.getDirectionX() == 1) ||
                (snakeHead.getPrevDirectionY() == -1 && snakeHead.getDirectionX() == -1)){
                oldHeadAsBody = new SnakeBodyCounterClockwise(snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
                snakeHead.setIsMovingX(true);
            // moving right to up -or- moving left to down (i.e. counterclockwise).
            } else if ((snakeHead.getPrevDirectionX() == 1 && snakeHead.getDirectionY() == -1) ||
                       (snakeHead.getPrevDirectionX() == -1 && snakeHead.getDirectionY() == 1)) {
                oldHeadAsBody = new SnakeBodyCounterClockwise(snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
                snakeHead.setIsMovingY(true);
            // moving up to right -or- down to left (i.e. clockwise).
            } else if ((snakeHead.getPrevDirectionY() == -1 && snakeHead.getDirectionX() == 1) ||
                       (snakeHead.getPrevDirectionY() == 1 && snakeHead.getDirectionX() == -1) ){
                oldHeadAsBody = new SnakeBodyClockwise(snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
                snakeHead.setIsMovingX(true);
            // moving right to down -or- moving left to up (i.e. clockwise).
            } else if ((snakeHead.getPrevDirectionX() == 1 && snakeHead.getDirectionY() == 1) ||
                       (snakeHead.getPrevDirectionX() == -1 && snakeHead.getDirectionY() == -1)) {
                oldHeadAsBody = new SnakeBodyClockwise(snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
                snakeHead.setIsMovingY(true);
            }
        }

        snakeEntities.set(0, oldHeadAsBody);  // replace snakehead with bodypart

        snakeEntities.addFirst(snakeHead); // add snakehead as new head.

        // Update the position of the head
        snakeHead.setPosition(
            newHeadPosition[0],
            newHeadPosition[1],
            calculateHeadAngle()
            );
        
        // Update the previous direction of the head
        snakeHead.updateDirection();

        // Set the cell with the new head to containing snake.
        grid.setCell(newHeadPosition[0], newHeadPosition[1], true);

        // Update the positions of the rest of the snake
        // updateSnakePositions();

        // Remove the tail segment if no apple was eaten
        if (!snakeHead.isAppleEaten()) {
            removeTailSegment();
        } else {
            // Apple was eaten, instead of removing tail, sets AppleEaten to false again
            snakeHead.setAppleEaten(false);
            apple.setX(Core.randomInt(0, 784));
            apple.setY(Core.randomInt(0, 784));
        }
        }
    }

    private void refreshRenderingEntities() {
        ArrayList<Entity> entities = new ArrayList<Entity>();

        entities.add(background);
        entities.add(apple);
        
        for (SnakeEntity entity : snakeEntities) {
            entities.add(entity);
        }

        if (isFirstMove) {
            entities.add(startGameInfo);
        }

        if (!snakeHead.isAlive()) {
            entities.add(gameOver);
        }

        gameLoop.sendScene(entities, getRunnables());
    }

    private int[] calculateNewHeadPosition() {
        int newHeadX = (int)snakeHead.getX()/S_C + snakeHead.getDirectionX();
        int newHeadY = (int)snakeHead.getY()/S_C + snakeHead.getDirectionY();
    
        // Grid dimensions (assuming a square grid for simplicity)
        int gridWidth = 800 / S_C;  // So even if window is resized, it will be for stage.
        int gridHeight = 800 / S_C;
    
        // Check for wrapping on the X-axis
        if (newHeadX >= gridWidth) {
            newHeadX = 0; // Wrap to the left side
        } else if (newHeadX < 0) {
            newHeadX = gridWidth - 1; // Wrap to the right side
        }
    
        // Check for wrapping on the Y-axis
        if (newHeadY >= gridHeight) {
            newHeadY = 0; // Wrap to the top
        } else if (newHeadY < 0) {
            newHeadY = gridHeight - 1; // Wrap to the bottom
        }
    
        return new int[]{newHeadX, newHeadY};
    }
    

    private double calculateHeadAngle() {
        if (snakeHead.getDirectionX() == 1) return 0;    // Right
        if (snakeHead.getDirectionX() == -1) return 180; // Left
        if (snakeHead.getDirectionY() == 1) return 90;   // Down
        if (snakeHead.getDirectionY() == -1) return 270; // Up
        return 0;
    }

    public void isFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    // Remove the last segment of the snake's body
    private void removeTailSegment() {

        SnakeEntity oldTail = snakeEntities.getLast();
        snakeEntities.removeLast(); // Assuming the tail is always the last element

        // Make second-last SnakeEntity to tail.
        SnakeEntity replaceBody = snakeEntities.getLast();

        // If the new tail is a body part, convert it to a tail
        SnakeTail newTail = new SnakeTail(replaceBody.getX(), replaceBody.getY(), replaceBody.getAngle());

        // Replace the body part with the new tail in the list
        snakeEntities.removeLast();
        snakeEntities.addLast(newTail);

        grid.setCell((int)oldTail.getX()/S_C, (int)oldTail.getY()/S_C, false);  // Leaves old spot.
        oldTail = null;
    }

    private ArrayList<Entity> createSnakeGameObjects() {
        ArrayList<Entity> entities = new ArrayList<Entity>();

        entities.add(background);
        entities.add(apple);

        for (SnakeEntity snakeEntity : snakeEntities) {
            entities.add(snakeEntity);
        }

        if (isFirstMove) {
           entities.add(startGameInfo);
        }
        
        createInputs(snakeHead);

        return entities;
    }

    private void createInputs(SnakeHead player) {
        InputEvents gameEvents = new GameEvents(player, this);

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
