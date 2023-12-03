package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Collision.SquareCollider;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Ground extends Entity implements Collidable {

    public Collider collider;

    public Ground() {
        super(new Material(
                    new Texture2D(Core.getSprite("base.png"), (int) Core.getStageWidth(), 100),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
              0, 
              Core.getStageHeight() - 100, 
              0, 
              1, 
              1);

        setCollider(new SquareCollider(this, getWidth(), getHeight()));
    }

    @Override
    public void update(double deltaTime) {}

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
