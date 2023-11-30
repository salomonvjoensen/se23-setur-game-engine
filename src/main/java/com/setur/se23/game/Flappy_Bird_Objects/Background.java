package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Background extends Entity {

    public Background(double xPos, double yPos) {
        super(new Material(
                    new Texture2D(Core.getSprite("background-day.png"), 800, 800),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
                xPos, yPos, 800, 800, 0, 1, 1);
    }

    @Override
    public void update(double deltaTime) {}
    
}
