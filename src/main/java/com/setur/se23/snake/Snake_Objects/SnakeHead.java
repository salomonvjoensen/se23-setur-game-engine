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
        if (moving) {
            directionX = 0;
            directionY = -1; // Upwards movement decreases Y-coordinate
            isMovingX = false;
            isMovingY = true;
        }
    }

    public void movingLeft(boolean moving) {
        if (moving) {
            directionX = -1; // Leftwards movement decreases X-coordinate
            directionY = 0;
            isMovingX = true;
            isMovingY = false;
        }
    }

    public void movingDown(boolean moving) {
        if (moving) {
            directionX = 0;
            directionY = 1; // Downwards movement increases Y-coordinate
            isMovingX = false;
            isMovingY = true;
        }
    }

    public void movingRight(boolean moving) {
        if (moving) {
            directionX = 1; // Rightwards movement increases X-coordinate
            directionY = 0;
            isMovingX = true;
            isMovingY = false;
        }
    }

    public int getDirectionX(){
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionX(int direction) {
        directionX = direction;
    }

    public void setDirectionY(int direction) {
        directionY = direction;
    }

    @Override
    public void collisionEvent(Entity collisionEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collisionEvent'");
    }
}
