package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Collision.SquareCollider;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Randoms;
import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.loop.Loop;
import com.setur.se23.engine.render.common.Texture2D;

public class Pipe extends Entity implements DynamicEntity, Collidable {

    public static boolean started;

    public static double speed;
    public boolean reverse;

    public Collider collider;


    public Pipe(boolean reverse, double xPos, double yPos) {
        super(new Texture2D(Resource.getSprite("pipe-green.png"), 52, 320), 
              xPos, 
              yPos, 
              reversePipe(reverse), 
              1.5, 
              1.68);

        this.reverse = reverse;

        speed = 100;
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

        double random = Randoms.randomDouble(1, 8);

        for (Entity entity : Loop.entities) {

            if (entity instanceof Pipe || entity instanceof ScoringHitBox) {
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

        if (entity instanceof ScoringHitBox) {

            ScoringHitBox scoreCollider = (ScoringHitBox) entity;

            if (scoreCollider.getX() < -100) {
    
                scoreCollider.setX(scoreCollider.getX() + 1200);
                scoreCollider.setY(random * 50 + 150);
            }
        }
    }
}
