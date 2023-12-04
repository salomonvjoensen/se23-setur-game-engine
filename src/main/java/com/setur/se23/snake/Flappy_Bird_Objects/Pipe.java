package com.setur.se23.snake.Flappy_Bird_Objects;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Collision.SquareCollider;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Pipe extends Entity implements Collidable {

    public double speed = 75;
    private boolean reverse;

    public Collider collider;

    public Pipe(double xPos, double yPos, int width, int height, double scaleX, double scaleY, boolean reverse) {
        super(new Material(
                new Texture2D(Core.getSprite("pipe-green.png"), width, height),
                new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
            xPos, yPos, width, height, reversePipe(reverse), scaleX, scaleY);

        this.reverse = reverse;

        setCollider(new SquareCollider(this, getWidth(), getHeight()));
    }

    private static double reversePipe(boolean reverse) {
        if (reverse) {
            return 180;
        } else {
            return 0;
        }
    }

    @Override
    public void update(double deltaTime) {
        setX(getX() - 1 * speed * deltaTime);

        if (getX() < -100) {
            if (reverse) {
            
                setX(getX() + 1200);

                setY(-500 + Core.randomInt(1, 9) * 50);
            } else {
                setX(getX() + 1200);

                setY(Core.previousRandomInt * 50 + 250);
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
    public void collisionEvent(Entity collisionEntity) {}
}
