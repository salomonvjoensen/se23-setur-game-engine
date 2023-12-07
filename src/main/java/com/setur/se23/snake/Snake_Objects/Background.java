package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Background extends Entity{

    public Background(Material material, double xPos, double yPos, double angle, double scaleX, double scaleY) {
        super(new Material(
                    new Texture2D(Core.getSprite("green-800-x-800.png"), 800, 800),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
              0, 
              0,
              0, 
              1, 
              1);
    }  
    
}
