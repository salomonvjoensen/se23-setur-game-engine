package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class SnakeHead extends Entity {

    private int directionX;
    private int directionY;

    public SnakeHead(double xPos, double yPos, double angle) {
            super(new Material(
                        new Texture2D(Core.getSprite("snake-head.png"), 160, 160),
                        new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                xPos, 
                yPos, 
                angle,
                0.1, 
                0.1);
        }

    public void setPosition(int i, int j) {

    }

    public void movingUp(boolean moving) {
        if (moving) {
            directionX = 0;
            directionY = -1; // Upwards movement decreases Y-coordinate
        }
    }

    public void movingLeft(boolean moving) {
        if (moving) {
            directionX = -1; // Leftwards movement decreases X-coordinate
            directionY = 0;
        }
    }

    public void movingDown(boolean moving) {
        if (moving) {
            directionX = 0;
            directionY = 1; // Downwards movement increases Y-coordinate
        }
    }

    public void movingRight(boolean moving) {
        if (moving) {
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
}
