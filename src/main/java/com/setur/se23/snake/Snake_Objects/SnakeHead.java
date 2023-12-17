package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.audio.SoundEffectsManager;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.snake.SnakeGameGUI;
import com.setur.se23.snake.SoundEffects;

/**
 * The Snake head, all the game logic is tied with the head,
 * and the rest of the body just follows suit.
 */
public class SnakeHead extends SnakeEntity{

    private int directionX;
    private int directionY;

    private int prevDirectionX = 1; // Initially moving right
    private int prevDirectionY = 0;

    private boolean isMovingX = true;
    private boolean isMovingY = false;
    private boolean isAlive = true;
    private boolean appleEaten = false;

    /**
     * Constructor.
     * 
     * @param xPos Sets horizontal position.
     * @param yPos Sets vertical position.
     * @param angle Sets the angle, initially 0 (facing right).
     */
    public SnakeHead(double xPos, double yPos, double angle) {
            super(new Material(
                        new Texture2D(Core.getSprite("snake-head.png"), 160, 160),
                        new MaterialColour(0.0f, 1.0f, 0.0f, 1.0f)), 
                xPos, 
                yPos, 
                angle);
            this.directionX = 1;  // Initially moving right.
            this.directionY = 0;

        }
    /**
     * Moving upwards, decreasing Y-coordinate by 1,
     * by updating vertical direction.
     * 
     * @param moving Boolean.
     */
    public void movingUp(boolean moving) {
        if (moving && isMovingX) {
            directionX = 0;
            directionY = -1; // Upwards movement decreases Y-coordinate
        }
    }

    /**
     * Moving left, decreasing X-coordinate by 1,
     * by updating horizontal direction.
     * 
     * @param moving Boolean.
     */
    public void movingLeft(boolean moving) {
        if (moving && isMovingY) {
            directionX = -1; // Leftwards movement decreases X-coordinate
            directionY = 0;
        }
    }

    /**
     * Moving down, increasing Y-coordinate by 1,
     * by updating vertical direction.
     * 
     * @param moving Boolean.
     */
    public void movingDown(boolean moving) {
        if (moving && isMovingX) {
            directionX = 0;
            directionY = 1; // Downwards movement increases Y-coordinate
        }
    }

    /**
     * Moving right, increasing X-coordinate by 1m
     * by updating horizontal direction.
     * 
     * @param moving Boolean.
     */
    public void movingRight(boolean moving) {
        if (moving && isMovingY) {
            directionX = 1; // Rightwards movement increases X-coordinate
            directionY = 0;
        }
    }

    /**
     * Get method.
     * 
     * @return Horizontal direction.
     */
    public int getDirectionX(){
        return directionX;
    }

    /**
     * Get method.
     * 
     * @return Vertical direction.
     */
    public int getDirectionY() {
        return directionY;
    }

    /**
     * Get method. Used to calculate bendy
     * snake bodypart.
     * 
     * @return Previous horizontal direction.
     */
    public int getPrevDirectionX() {
        return prevDirectionX;
    }

    /**
     * Get method. Used to calculate bendy
     * snake bodypart.
     * 
     * @return Previous vertical direction.
     */

    public int getPrevDirectionY() {
        return prevDirectionY;
    }

    /**
     * Set method for horizontal direction.
     * 
     * @param direction Horizontal direction.
     */
    public void setDirectionX(int direction) {
        directionX = direction;
    }

    /**
     * Set method for vertical direction.
     * 
     * @param direction vertical direction.
     */
    public void setDirectionY(int direction) {
        directionY = direction;
    }

    /**
     * Old directions updated,
     * used in calculating which
     * bendy snake bodypart to use.
     */
    public void updateDirection() {
        prevDirectionX = directionX;
        prevDirectionY = directionY;
    }

    /**
     * Get method to check for if direction was changed.
     * 
     * @return Boolean for checking if the previous direction has been changed
     */
    public boolean isDirectionChanged() {
        return prevDirectionX != directionX || prevDirectionY != directionY;
    }

    /**
     * Get method.
     * 
     * @return Boolean to check for if snake is moving in the horizontal direction.
     */
    public boolean getIsMovingX() {
        return isMovingX;
    }

    /**
     * Get method.
     * 
     * @return Boolean to check for if snake is moving in the vertical direction.
     */
    public boolean getIsMovingY() {
        return isMovingY;
    }

    /**
     * Set method, interchange between moving from/to vertical to horizontal.
     * 
     * @param movingX Boolean for horizontal movement.
     */
    public void setIsMovingX(boolean movingX) {
        isMovingX = movingX;
        isMovingY = !movingX;
    }

    /**
     * Set method, interchange between moving from/to horizontal to vertical.
     * 
     * @param movingY Boolean for vertical movement.
     */
    public void setIsMovingY(boolean movingY) {
        isMovingY = movingY;
        isMovingX = !movingY;
    }
    
    /**
     * Set method.
     * 
     * @param eating Boolean for eating Apple.
     */
    public void setAppleEaten(boolean eating) {
        appleEaten = eating;
    }
    
    /**
     * Get method.
     * 
     * @return Boolean for eating Apple.
     */
    public boolean isAppleEaten() {
        return appleEaten;
    }

    /**
     * Get method used in collision check.
     * 
     * @return Boolean for if snake is alive.
     */
    public boolean isAlive() {
        return isAlive;
    }
    
    /**
     * For registering collision.
     * 
     * @param collisionEntity Collision either between Apple or the snake's own body.
     */
    @Override
    public void collisionEvent(Entity collisionEntity) {
        if (collisionEntity instanceof Apple && !appleEaten) {
            SoundEffectsManager.playLoaded(SoundEffects.EAT_APPLE.getFilePath());
            appleEaten = true;
        }

        if (collisionEntity instanceof SnakeEntity && isAlive) {
            SoundEffectsManager.playLoaded(SoundEffects.SNAKE_HISS.getFilePath());
            SnakeGameGUI.gameOver();
            isAlive = false;
        }
    }

}
