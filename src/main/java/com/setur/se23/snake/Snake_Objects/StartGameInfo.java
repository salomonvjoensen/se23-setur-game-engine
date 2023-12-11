package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.snake.SnakeGlobals;

/**
 * The start game info displayed before any input is pressed other than WASD.
 */
public class StartGameInfo extends Entity{

    private static final double PIXEL_SIZE = SnakeGlobals.PIXEL_SIZE;  // Default 1.

    /**
     * Constructor.
     */
    public StartGameInfo() {
        super(new Material(
                    new Texture2D(Core.getSprite("start-game-info.png"), 651, 409),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
              100*PIXEL_SIZE, 
              100*PIXEL_SIZE,
              0, 
              1*PIXEL_SIZE, 
              1*PIXEL_SIZE);
    }  
    
}
