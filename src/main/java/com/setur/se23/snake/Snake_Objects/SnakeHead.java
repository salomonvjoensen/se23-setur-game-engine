package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class SnakeHead extends SnakeEntity{

    private int directionX;
    private int directionY;

    private int prevDirectionX = 1; // Initially moving right
    private int prevDirectionY = 0;

    private boolean isMovingX = true;
    private boolean isMovingY = false;
    private boolean isAlive = true;

    public Collider collider;

    public SnakeHead(double xPos, double yPos, double angle) {
            super(new Material(
                        new Texture2D(Core.getSprite("snake-head.png"), 160, 160),
                        new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                xPos, 
                yPos, 
                angle,
                0.1, 
                0.1);
            this.directionX = 1;  // Initially moving right.
            this.directionY = 0;

        }
    
    public void movingUp(boolean moving) {
        if (moving && isMovingX) {
            directionX = 0;
            directionY = -1; // Upwards movement decreases Y-coordinate
        }
    }

    public void movingLeft(boolean moving) {
        if (moving && isMovingY) {
            directionX = -1; // Leftwards movement decreases X-coordinate
            directionY = 0;
        }
    }

    public void movingDown(boolean moving) {
        if (moving && isMovingX) {
            directionX = 0;
            directionY = 1; // Downwards movement increases Y-coordinate
        }
    }

    public void movingRight(boolean moving) {
        if (moving && isMovingY) {
            directionX = 1; // Rightwards movement increases X-coordinate
            directionY = 0;
        }
    }

    public int getDirectionX(){
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public int getPrevDirectionX() {
        return prevDirectionX;
    }

    public int getPrevDirectionY() {
        return prevDirectionY;
    }

    public void setDirectionX(int direction) {
        directionX = direction;
    }

    public void setDirectionY(int direction) {
        directionY = direction;
    }

    public void updateDirection() {
        prevDirectionX = directionX;
        prevDirectionY = directionY;
    }

    public boolean isDirectionChanged() {
        return prevDirectionX != directionX || prevDirectionY != directionY;
    }

    public boolean getIsMovingX() {
        return isMovingX;
    }

    public boolean getIsMovingY() {
        return isMovingY;
    }

    public void setIsMovingX(boolean movingX) {
        isMovingX = movingX;
        isMovingY = !movingX;
    }

    public void setIsMovingY(boolean movingY) {
        isMovingY = movingY;
        isMovingX = !movingY;
    }

    @Override
    public void collisionEvent(Entity collisionEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collisionEvent'");
    }
}
