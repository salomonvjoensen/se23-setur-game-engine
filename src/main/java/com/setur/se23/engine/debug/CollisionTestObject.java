package com.setur.se23.engine.debug;

import com.setur.se23.engine.Collision.CircleCollider;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.game.Flappy_Bird_Objects.Bird;
import com.setur.se23.game.Flappy_Bird_Objects.Ground;
import com.setur.se23.game.Flappy_Bird_Objects.Pipe;

public class CollisionTestObject extends Entity implements Collidable {

    private double speed = 100;

    private int i = 0;

    public double moveLeft = 0;
    public double moveRight = 0;
    public double moveUp = 0;
    public double moveDown = 0;

    public Collider collider;


    public CollisionTestObject(double xPos, double yPos, int width, int height) {
        super(new Material(
                    new Texture2D(Core.getSprite("flappy-bird.png"), width, height),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                xPos, yPos, width, height, 0, 1, 1);

        setCollider(new CircleCollider(this, getHeight() / 2));
    }

    public void movingLeft(boolean val) {
        if (val) {
            moveLeft = -1;
        } else {
            moveLeft = 0;
        }
    }
   
    public void movingRight(boolean val) {
        if (val) {
            moveRight = 1;
        } else {
            moveRight = 0;
        }
    }
   
    public void movingUp(boolean val) {
        if (val) {
            moveUp = -1;
        } else {
            moveUp = 0;
        }
    }
   
    public void movingDown(boolean val) {
        if (val) {
            moveDown = 1;
        } else {
            moveDown = 0;
        }
    }

    @Override
    public void update(double deltaTime) {
        setX(getX() + (moveLeft + moveRight) * speed * deltaTime);
        setY(getY() + (moveUp + moveDown) * speed * deltaTime);
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

        //clears the console
        Core.debug.clearConsole();

        if (collisionEntity instanceof Pipe) {
            Core.debug.warning("pipe" + i++);
        }

        if (collisionEntity instanceof Ground) {
            Core.debug.warning("ground" + i++);
        }

        if (collisionEntity instanceof Bird) {
            Core.debug.warning("bird" + i++);
        }
    }
}
