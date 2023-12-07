package com.setur.se23.engine.debug;

import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Bird;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Ground;
import com.setur.se23.FlappyBird.Flappy_Bird_Objects.Pipe;
import com.setur.se23.engine.Collision.CircleCollider;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.GUI.GUI;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class CollisionTestObject extends Entity implements DynamicEntity, Collidable {

    private double speed = 100;

    private int i = 0;

    public double moveLeft = 0;
    public double moveRight = 0;
    public double moveUp = 0;
    public double moveDown = 0;

    public Collider collider;


    public CollisionTestObject(double xPos, double yPos) {
        super(new Material(
                    new Texture2D(GUI.convertImageToByteArray(Core.getResorcePath("sprites/flappy-bird.png")), 280, 200),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
                xPos, 
                yPos, 
                0,
                0.5,
                0.5);

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
