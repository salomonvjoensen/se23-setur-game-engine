package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Collision.SquareCollider;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class ScoringHitBox extends Entity implements DynamicEntity, Collidable {

    public Collider collider;

    public ScoringHitBox(double xPos, double yPos) {
        super(new Material(
                    new Texture2D(Resource.getSprite("pipe-green.png"), 0, 0),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
              xPos, 
              yPos, 
              0, 
              1, 
              1);


        setCollider(new SquareCollider(this, 25, 250));
    }

    @Override
    public void update(double deltaTime) {
        if (Pipe.started) {
            setX(getX() - 1 * Pipe.speed * deltaTime);
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
