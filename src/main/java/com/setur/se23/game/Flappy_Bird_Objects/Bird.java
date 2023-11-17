package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Bird extends DynamicEntity {

    public Bird(double xPos, double yPos, double speed) {
        super(new Material(
                    new Texture2D(Core.getResorcePath("sprites/flappy-bird.png"), 40, 30),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                xPos, 
                yPos, 
                speed);
    }

    @Override
    public void update(double deltaTime) {
        setXDir(1);
        setX(getX() + getXDir() * getSpeed() * deltaTime);
        setY(getY() + getYDir() * getSpeed() * deltaTime);
    }
    
    
}
