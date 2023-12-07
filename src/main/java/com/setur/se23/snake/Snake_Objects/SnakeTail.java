package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class SnakeTail extends Entity {

public SnakeTail(double xPos, double yPos, double angle) {
        super(new Material(
                    new Texture2D(Core.getSprite("snake-tail.png"), 110, 90),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
              xPos, 
              yPos, 
              angle,
              0.1, 
              0.1);
    }  
}
