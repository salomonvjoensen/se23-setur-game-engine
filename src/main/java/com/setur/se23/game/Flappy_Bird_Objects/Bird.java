package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Bird extends Entity {

    private double fallAccel = 1.25;
    private double fallSpeed = 10;

    private double velocityY;

    public Bird(double xPos, double yPos, int width, int height) {
        super(new Material(
                    new Texture2D(Core.getResorcePath("sprites/flappy-bird.png"), width, height),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                xPos, 
                yPos,
                width,
                height);
    }

    public void setFallSpeed(double fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public void update(double deltaTime) {
        fallSpeed *= fallAccel;

        if (fallSpeed > 1200) {
            fallSpeed = 1200;
        }

        velocityY += fallSpeed * deltaTime;
        setY(getY() + velocityY * deltaTime);

        if (getY() < 0) {
            setY(0);
        }

        if (getY() > 650) {
            setY(650);
        }
    }
    
    
}
