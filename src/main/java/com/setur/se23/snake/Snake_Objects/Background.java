package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.snake.SnakeGlobals;

/**
 * The green background in the Snake game.
 */
public class Background extends Entity{

    private static final int SCREEN_SIZE = SnakeGlobals.SCREEN_SIZE;

    /**
     * Constructor.
     */
    public Background() {
        super(new Texture2D(Resource.getSprite("green-800-x-800.png"), SCREEN_SIZE, SCREEN_SIZE), 
              0, 
              0,
              0, 
              1, 
              1);
    }  
    
}
