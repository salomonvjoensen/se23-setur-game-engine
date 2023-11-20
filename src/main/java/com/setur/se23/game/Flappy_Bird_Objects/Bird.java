package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Bird extends DynamicEntity {

    private double fallAccel = 1.25;
    private double fallSpeed = 10;

    public Bird(double xPos, double yPos) {
        super(new Material(
                    new Texture2D(Core.getResorcePath("sprites/flappy-bird.png"), 40, 30),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                xPos, 
                yPos, 
                0);
    }

    public void setFallSpeed(double fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    @Override
    public void update(double deltaTime) {
        fallSpeed *= fallAccel;

        if (fallSpeed > 1500) {
            fallSpeed = 1200;
        }

        setYDir(getYDir() + fallSpeed * deltaTime);
        setY(getY() + getYDir() * deltaTime);
    }
    
    
}
