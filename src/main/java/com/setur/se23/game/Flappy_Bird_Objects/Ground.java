package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Ground extends Entity {

    public Ground(double xPos, double yPos) {
        super(new Material(
                    new Texture2D(Core.getResorcePath("sprites/base.png"), (int) Core.getStageWidth(), 100),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
                xPos, 
                yPos);
    }

    @Override
    public void update(double deltaTime) {
        
    }
    
}