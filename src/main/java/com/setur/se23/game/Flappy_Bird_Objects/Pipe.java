package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Pipe extends DynamicEntity {

    public Pipe(double xPos, double yPos, int width, int height) {
        super(new Material(
                    new Texture2D(Core.getResorcePath("sprites/pipe-green.png"), width, height),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
                xPos,
                yPos,
                100);
    }

    @Override
    public void update(double deltaTime) {
        setX(getX() - 1 * getSpeed() * deltaTime);
    }
    
}