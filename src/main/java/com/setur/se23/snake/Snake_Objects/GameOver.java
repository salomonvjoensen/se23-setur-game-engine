package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

/**
 * The game over screen, shown when Snake collides with itself.
 */
public class GameOver extends Entity{

    /**
     * Constructor.
     */
    public GameOver() {
        super(new Material(
                    new Texture2D(Core.getSprite("gameover.png"), 192, 42),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
              300, 
              384,
              0, 
              1, 
              1);
    }  
    
}
