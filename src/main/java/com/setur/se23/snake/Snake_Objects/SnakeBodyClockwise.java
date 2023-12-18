package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.common.Texture2D;

/**
 * The clockwise bendy bodypart of the Snake.
 */
public class SnakeBodyClockwise extends SnakeEntity {

    /**
     * Constructor.
     * 
     * @param xPos Sets horizontal position.
     * @param yPos Sets vertical position.
     * @param angle Sets the angle.
     */
    public SnakeBodyClockwise(double xPos, double yPos, double angle) {
        super(new Texture2D(Resource.getSprite("Snake-body-up-right.png"), 160, 160), 
              xPos, 
              yPos, 
              angle);
    }

}
