package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

/**
 * The start game info displayed before any input is pressed other than WASD.
 */
public class StartGameInfo extends Entity{

    /**
     * Constructor.
     */
    public StartGameInfo() {
        super(new Material(
                    new Texture2D(Core.getSprite("start-game-info.png"), 651, 409),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
              100, 
              100,
              0, 
              1, 
              1);
    }  
    
}
