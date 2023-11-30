package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.loop.Loop;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Bird extends Entity implements Collidable {

    private double fallAccel = 1.25;
    private double fallSpeed = 10;

    private double velocityY;

    private boolean alive = true;
    private boolean grounded = false;
    public boolean jumpReady = true;

    public Bird(double xPos, double yPos, int width, int height) {
        super(new Material(
                    new Texture2D(Core.getSprite("flappy-bird.png"), width, height),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                xPos, yPos, width, height, 0);
    }

    public void jump() {
        if (alive && jumpReady) {
            velocityY = -200;
            fallSpeed = 10;
            jumpReady = false;
        }
    }

    @Override
    public void update(double deltaTime) {

        if (grounded) {
            setY(660);
        } else {

            fallSpeed *= fallAccel;

            if (fallSpeed > 1200) {
                fallSpeed = 1200;
            }

            setAngle(velocityY / 20);

            velocityY += fallSpeed * deltaTime;
            setY(getY() + velocityY * deltaTime);

            if (getY() < 0) {
                setY(0);
            }
        }
    }

    @Override
    public void collisionEvent(Entity collisionEntity) {

        if (collisionEntity instanceof Pipe) {
            alive = false;
        }

        if (collisionEntity instanceof Ground) {
            alive = false;
            grounded = true;

            stopPipes();
        }
    }

    private void stopPipes() {
        for (Entity entity : Loop.entities) {
            if (entity instanceof Pipe) {
                ((Pipe) entity).speed = 0;
            }
        }
    }
}
