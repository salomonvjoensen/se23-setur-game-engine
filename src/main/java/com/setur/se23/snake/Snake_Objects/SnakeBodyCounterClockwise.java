package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

/**
 * The counterclockwise bendy bodypart of the Snake.
 */
public class SnakeBodyCounterClockwise extends SnakeEntity {

    /**
     * Constructor.
     * 
     * @param xPos Sets horizontal position.
     * @param yPos Sets vertical position.
     * @param angle Sets the angle.
     */
    public SnakeBodyCounterClockwise(double xPos, double yPos, double angle) {
        super(new Material(
                    new Texture2D(Core.getSprite("Snake-body-down-right.png"), 160, 160),
                    new MaterialColour(0.0f, 1.0f, 0.0f, 1.0f)), 
              xPos, 
              yPos, 
              angle);
    }

}
