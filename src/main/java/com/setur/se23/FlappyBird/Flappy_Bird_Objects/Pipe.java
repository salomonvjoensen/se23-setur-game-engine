package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Collision.SquareCollider;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.loop.Loop;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Pipe extends Entity implements DynamicEntity, Collidable {

    public static boolean started;

    public static double speed;
    public boolean reverse;

    public Collider collider;


    public Pipe(boolean reverse, double xPos, double yPos) {
        super(new Material(
                    new Texture2D(Core.getSprite("pipe-green.png"), 52, 320),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
              xPos, 
              yPos, 
              reversePipe(reverse), 
              1.5, 
              1.68);

        this.reverse = reverse;

        speed = 90;
        started = false;

        setCollider(new SquareCollider(this, getWidth(), getHeight()));
    }

    private static double reversePipe(boolean reverse) {
        if (reverse) {
            return 180;
        } else {
            return 0;
        }
    }

    public static void start() {
        started = true;
    }

    public static void stop() {
        started = false;
    }

    @Override
    public void update(double deltaTime) {
        if (started) {
            setX(getX() - 1 * speed * deltaTime);
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
    public void collisionEvent(Entity collisionEntity) {}



    public static void loopAroundPipes() {

        double random = Core.randomDouble(1, 8);

        for (Entity entity : Loop.entities) {

            if (entity instanceof Pipe || entity instanceof ScoreCollider) {
                Pipe.loopAroundPipe(entity, random);
            }
        }
    }

    private static void loopAroundPipe(Entity entity, double random) {

        if (entity instanceof Pipe) {

            Pipe pipe = (Pipe) entity;

            if (pipe.getX() < -100) {
    
                if (pipe.reverse) {
                    pipe.setX(pipe.getX() + 1200);
                    pipe.setY(random * 50 - 500);
                } else {
                    pipe.setX(pipe.getX() + 1200);
                    pipe.setY(random * 50 + 250);
                }
            }
        }

        if (entity instanceof ScoreCollider) {

            ScoreCollider scoreCollider = (ScoreCollider) entity;

            if (scoreCollider.getX() < -100) {
    
                scoreCollider.setX(scoreCollider.getX() + 1200);
                scoreCollider.setY(random * 50 + 150);
            }
        }
    }
}
