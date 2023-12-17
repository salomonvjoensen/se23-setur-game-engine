package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.common.Texture2D;

/**
 * The snake tail body part.
 * 
 * It replaces for each iteration the 2nd last body part,
 * since it is deleted for each gameLoop
 */
public class SnakeTail extends SnakeEntity{

    /**
     * Constructor.
     * 
     * @param xPos Sets horizontal position.
     * @param yPos Sets vertical position.
     * @param angle Sets the angle, initially 0 (facing right).
     */
    public SnakeTail(double xPos, double yPos, double angle) {
        super(new Texture2D(Resource.getSprite("snake-tail.png"), 160, 160), 
                xPos, 
                yPos, 
                angle);
    }
}
