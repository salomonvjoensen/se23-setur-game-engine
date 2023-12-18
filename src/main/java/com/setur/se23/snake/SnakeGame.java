package com.setur.se23.snake;

import java.util.ArrayList;

import com.setur.se23.engine.audio.SoundEffectsManager;
import com.setur.se23.dependency.input.FX_Input;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Randoms;
import com.setur.se23.engine.input.InputEvents;
import com.setur.se23.engine.input.InputManager;
import com.setur.se23.engine.input.InputType;
import com.setur.se23.engine.loop.GameLoop;
import com.setur.se23.engine.loop.Loop;
import com.setur.se23.snake.Snake_Objects.Apple;
import com.setur.se23.snake.Snake_Objects.Background;
import com.setur.se23.snake.Snake_Objects.Snake;
import com.setur.se23.snake.Snake_Objects.SnakePart;
import com.setur.se23.snake.Snake_Objects.StartGameInfo;

/**
 * The Snake game. Contains all the game logic.
 */
public class SnakeGame {

    private long lastMoveTime = System.currentTimeMillis();
    private static final double SCREEN_SIZE = SnakeGlobals.SCREEN_SIZE;  // Default 800
    private static final int GRID_SIZE = SnakeGlobals.GRID_SIZE;  // Default 50
    private static final long MOVE_INTERVAL = SnakeGlobals.MOVE_INTERVAL;  // 200ms between updates.
    private static final int C_S = SnakeGlobals.C_S;  // Cell size. (default 16)
    private static final int HEAD_START_POSITION_X = GRID_SIZE/2;
    private static final int HEAD_START_POSITION_Y = GRID_SIZE/2;

    private Grid grid;
    private ArrayList<Snake> snakeEntities;
    private Background background;
    private Apple apple;
    private Snake snakeHead;
    private Snake snakeBody1;
    private Snake snakeBody2;
    private Snake snakeBody3;
    private Snake snakeTail;
    private StartGameInfo startGameInfo;

    // can be -1, 0 or 1.
    public int directionX;
    public int directionY;
    
    private boolean isFirstMove;

    public GameLoop gameLoop = GameLoop.getInstance();
    public Loop loop;

    /**
     * Constructor.
     */
    public SnakeGame() {
        this.background = new Background();
        this.apple = new Apple(Randoms.randomDouble(0, SCREEN_SIZE-C_S), Randoms.randomDouble(0, SCREEN_SIZE-C_S));
        this.grid = new Grid(GRID_SIZE, GRID_SIZE);
        this.startGameInfo = new StartGameInfo();
        this.snakeEntities = new ArrayList<Snake>();
        this.isFirstMove = true;

        loadSoundEffects();

        SnakeGameGUI.setRestartRunnable(() -> initSnakeAndObjects());
        initSnakeAndObjects();
    }

    /**
     * Used in the Constructor and when pressing the 'R' button.
     */
    public void initSnakeAndObjects() {
        SnakeGameGUI.newGame();
        SnakeGameGUI.setGUI();
        // can be between 4-49 for the x position on the default grid,
        // and 0-49 for the y position on the default grid.
        initializeSnake(HEAD_START_POSITION_X, HEAD_START_POSITION_Y);        

        newScene();
    }

    /**
     * What is sent to the game loop.
     */
    private void newScene() {

        gameLoop.unsubscribeFromFrame(loop);

        loop = new Loop(createSnakeGameObjects(), getRunnables());

        gameLoop.subscribeToFrame(loop);

    }

    /**
     * Initialize the snake.
     * 
     * @param posX The initial grid X positions of the snake.
     * @param posY The initial grid Y positions of the snake.
     */
    private void initializeSnake(int posX, int posY) {
        snakeEntities = new ArrayList<Snake>();

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

        snakeHead = new Snake(SnakePart.HEAD.getTexture(), headStageCoordinates[0], headStageCoordinates[1], 0);
        snakeBody1 = new Snake(SnakePart.BODY.getTexture() , bodyStageCoordinates1[0], bodyStageCoordinates1[1], 0);
        snakeBody2 = new Snake(SnakePart.BODY.getTexture() ,bodyStageCoordinates2[0], bodyStageCoordinates2[1], 0);
        snakeBody3 = new Snake(SnakePart.BODY.getTexture() ,bodyStageCoordinates3[0], bodyStageCoordinates3[1], 0);
        snakeTail = new Snake(SnakePart.TAIL.getTexture() ,tailStageCoordinates[0], tailStageCoordinates[1], 0);



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

    /**
     * Logical movement of the snake.
     */
    public void moveSnake() {
        if (snakeHead.isAlive() && !isFirstMove) {
            int[] newHeadPosition = calculateNewHeadPosition();

            // Convert the old head into a body part, defaults to SnakeBody.
            Snake oldHeadAsBody = new Snake(SnakePart.BODY.getTexture(), snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
            
            // Before updating the head's position, check if the direction has changed
            // Replace the body part behind the head with the appropriate bendy part
            // Determine the appropriate bendy part based on direction, --8-- possibilities, with 4 different conditions.
            if (snakeHead.isDirectionChanged()) {
                // moving down to right -or- moving up to left (i.e. counterclockwise).
                if ((snakeHead.getPrevDirectionY() == 1 && snakeHead.getDirectionX() == 1) ||
                    (snakeHead.getPrevDirectionY() == -1 && snakeHead.getDirectionX() == -1)){
                    oldHeadAsBody = new Snake(SnakePart.BODY_COUNTER_CLOCKWISE.getTexture(), snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
                    snakeHead.setIsMovingX(true);
                // moving right to up -or- moving left to down (i.e. counterclockwise).
                } else if ((snakeHead.getPrevDirectionX() == 1 && snakeHead.getDirectionY() == -1) ||
                        (snakeHead.getPrevDirectionX() == -1 && snakeHead.getDirectionY() == 1)) {
                    oldHeadAsBody = new Snake(SnakePart.BODY_COUNTER_CLOCKWISE.getTexture(), snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
                    snakeHead.setIsMovingY(true);
                // moving up to right -or- down to left (i.e. clockwise).
                } else if ((snakeHead.getPrevDirectionY() == -1 && snakeHead.getDirectionX() == 1) ||
                        (snakeHead.getPrevDirectionY() == 1 && snakeHead.getDirectionX() == -1) ){
                    oldHeadAsBody = new Snake(SnakePart.BODY_CLOCKWISE.getTexture(), snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
                    snakeHead.setIsMovingX(true);
                // moving right to down -or- moving left to up (i.e. clockwise).
                } else if ((snakeHead.getPrevDirectionX() == 1 && snakeHead.getDirectionY() == 1) ||
                        (snakeHead.getPrevDirectionX() == -1 && snakeHead.getDirectionY() == -1)) {
                    oldHeadAsBody = new Snake(SnakePart.BODY_CLOCKWISE.getTexture(), snakeHead.getX(), snakeHead.getY(), calculateHeadAngle());
                    snakeHead.setIsMovingY(true);
                }
            }

            snakeEntities.set(0, oldHeadAsBody);  // replace snakehead with bodypart

            snakeEntities.addFirst(snakeHead);  // add snakehead as new head.

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

            // Remove the tail segment if no apple was eaten
            if (!snakeHead.isAppleEaten()) {
                removeTailSegment();
            } else {
                // Apple was eaten, instead of removing tail,
                // sets AppleEaten to false again
                // and move the Apple to a new random location.
                snakeHead.setAppleEaten(false);
                apple.setX(Randoms.randomDouble(0, SCREEN_SIZE-C_S));
                apple.setY(Randoms.randomDouble(0, SCREEN_SIZE-C_S));
            }
        }
    }

    /**
     * Method for refreshing all the entities in the scene.
     */
    private void refreshRenderingEntities() {
        Loop.entities.clear();

        Loop.entities.add(background);
        Loop.entities.add(apple);
        
        for (Snake entity : snakeEntities) {
            Loop.entities.add(entity);
        }

        // If it is the first move, displays info about movement and how to restart.
        if (isFirstMove) {
            Loop.entities.add(startGameInfo);
        }

        // If the snake collides with itself display the Game Over object.
        loop.assignlists();
    }

    /**
     * Method to calculate, depending on movement,
     * where the new head position should be.
     * 
     * @return
     */
    private int[] calculateNewHeadPosition() {
        int newHeadX = (int)snakeHead.getX()/C_S + snakeHead.getDirectionX();
        int newHeadY = (int)snakeHead.getY()/C_S + snakeHead.getDirectionY();
    
        // Check for wrapping on the X-axis
        if (newHeadX >= grid.getSizeX()) {
            newHeadX = 0; // Wrap to the left side
        } else if (newHeadX < 0) {
            newHeadX = grid.getSizeX() - 1; // Wrap to the right side
        }
    
        // Check for wrapping on the Y-axis
        if (newHeadY >= grid.getSizeY()) {
            newHeadY = 0; // Wrap to the top
        } else if (newHeadY < 0) {
            newHeadY = grid.getSizeY() - 1; // Wrap to the bottom
        }
    
        return new int[]{newHeadX, newHeadY};
    }
    
    /**
     * Calculate angle of head, depending on direction.
     * 
     * @return Depending on direction, returns the angle of the Head.
     */
    private double calculateHeadAngle() {
        if (snakeHead.getDirectionX() == 1) return 0;    // Right
        if (snakeHead.getDirectionX() == -1) return 180; // Left
        if (snakeHead.getDirectionY() == 1) return 90;   // Down
        if (snakeHead.getDirectionY() == -1) return 270; // Up
        return 0;
    }

    /**
     * Get method to determine if it is the first move.
     * 
     * @param firstMove Boolean for first move.
     */
    public void isFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    /**
     * Remove the last segment of the snake's body
     */
    private void removeTailSegment() {

        Snake oldTail = snakeEntities.getLast();
        snakeEntities.removeLast(); // Assuming the tail is always the last element

        // Make second-last SnakeEntity to tail.
        Snake replaceBody = snakeEntities.getLast();

        // If the new tail is a body part, convert it to a tail
        Snake newTail = new Snake(SnakePart.TAIL.getTexture(), replaceBody.getX(), replaceBody.getY(), replaceBody.getAngle());

        // Replace the body part with the new tail in the list
        snakeEntities.removeLast();
        snakeEntities.addLast(newTail);

        // Leaves old spot as false size
        grid.setCell(GridUtils.stageCoordinateXtoGrid((int)oldTail.getX()), GridUtils.stageCoordinateYtoGrid((int)oldTail.getY()), false);  
        oldTail = null;
    }

    /**
     * Method for creating all the objects,
     * that will be drawn on the scene.
     * 
     * @return The entities that will be drawn on the scene.
     */
    private ArrayList<Entity> createSnakeGameObjects() {
        ArrayList<Entity> entities = new ArrayList<Entity>();

        entities.add(background);
        entities.add(apple);

        for (Snake snakeEntity : snakeEntities) {
            entities.add(snakeEntity);
        }

        if (isFirstMove) {
           entities.add(startGameInfo);
        }
        
        createInputs(snakeHead);

        return entities;
    }


    private void initializeInputManager(InputEvents gameEvents) {
        var inputSystem = new FX_Input();

        InputManager.Instantiate(inputSystem)
                .initialize(gameEvents);
    }

    /**
     * Create the inputs for the player.
     * 
     * @param player The inputs are valid for the SnakeHead.
     */
    private void createInputs(Snake player) {
        initializeInputManager(new GameEvents(player, this));

        InputManager inputSystem = InputManager.getInstance();

        inputSystem.addInput(InputType.onPress, "P", "toggle_FPS_Counter");
        inputSystem.addInput(InputType.onPress, "O", "toggle_Gizmos");
        inputSystem.addInput(InputType.onPress, "R", "Restart");

        // Onkey press.
        inputSystem.addInput(InputType.onPress, "W", "Up");
        inputSystem.addInput(InputType.onPress, "S", "Down");
        inputSystem.addInput(InputType.onPress, "A", "Left");
        inputSystem.addInput(InputType.onPress, "D", "Right");

        // Onkey release.
        inputSystem.addInput(InputType.onRelease, "W", "Up_Ready");
        inputSystem.addInput(InputType.onRelease, "S", "Down_Ready");
        inputSystem.addInput(InputType.onRelease, "A", "Left_Ready");
        inputSystem.addInput(InputType.onRelease, "D", "Right_Ready");

        inputSystem.setInputs();
    }

    /**
     * The runnables that are sent to the game loop.
     * This game uses refreshing calculations as Runnables.
     * 
     * @return Runnable of moving snake and refreshing all entities.
     */
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

    /**
     * Preload sound effects into memory.
     */
    private void loadSoundEffects() {
        SoundEffectsManager.loadSoundEffect(SoundEffects.EAT_APPLE.getFilePath());
        SoundEffectsManager.loadSoundEffect(SoundEffects.SNAKE_HISS.getFilePath());
    }
}
