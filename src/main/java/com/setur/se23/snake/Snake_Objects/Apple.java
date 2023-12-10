package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.Collision.CircleCollider;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Apple extends Entity implements Collidable{

    Collider collider;

    public Apple(double xPos, double yPos) {
                super(new Material(
                    new Texture2D(Core.getSprite("apple.png"), 160, 160),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
              xPos, 
              yPos, 
              0,
              0.1, 
              0.1);
        
        setCollider(new CircleCollider(this, getHeight() / 2));
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
    }  
}