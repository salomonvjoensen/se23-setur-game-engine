package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.common.Texture2D;

/**
 * The straight body part of the Snake.
 */
public class SnakeBody extends SnakeEntity{

    /**
     * Constructor.
     * 
     * @param xPos Sets horizontal position.
     * @param yPos Sets vertical position.
     * @param angle Sets the angle, initially 0 (facing right).
     */
    public SnakeBody(double xPos, double yPos, double angle) {
        super(new Texture2D(Resource.getSprite("snake-body.png"), 160, 160), 
              xPos, 
              yPos, 
              angle);
    }
}
