package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

import com.setur.se23.FlappyBird.Score;
import com.setur.se23.engine.Collision.CircleCollider;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Bird extends Entity implements DynamicEntity, Collidable {

    private double fallAccel = 1.25;
    private double fallSpeed = 10;
    private double velocityY;

    private boolean alive = true;
    private boolean airborn = true;
    private boolean started = false;

    private Entity prevScoreCollider;

    public boolean jumpReady = true;

    public Collider collider;


    public Bird(double xPos, double yPos) {
        super(new Material(
                    new Texture2D(Core.getSprite("flappy-bird.png"), 280, 200),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
              xPos, 
              yPos, 
              0, 
              0.2, 
              0.2);

        setCollider(new CircleCollider(this, getHeight() / 2));
    }

    public void jump() {
        if (alive && jumpReady) {

            velocityY = -200;
            fallSpeed = 10;
            
            jumpReady = false;

            started = true;
            Pipe.started = true;
        }
    }

    @Override
    public void update(double deltaTime) {

        if (airborn && started) {

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
    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    @Override
    public Collider getCollider() {
        return collider;
    }

    @Override
    public void collisionEvent(Entity collisionEntity) {

        if (collisionEntity instanceof Pipe) {
            alive = false;
        }

        if (collisionEntity instanceof Ground) {
            alive = false;
            airborn = false;

            Pipe.speed = 0;
        }

        if (collisionEntity instanceof ScoreCollider) {

            if (collisionEntity.equals(prevScoreCollider) == false) {
                Score.updateScore(1);

                prevScoreCollider = collisionEntity;
            }
        }
    }
}
